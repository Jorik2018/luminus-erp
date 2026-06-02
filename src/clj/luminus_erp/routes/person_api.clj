;; src/clj/luminus_erp/routes/person_api.clj
(ns luminus-erp.routes.person-api
  (:require
    [clojure.string :as str]
    [luminus-erp.db.core :as db]
    [luminus-erp.middleware :as middleware]
    [ring.util.http-response :as response]))

(defn- parse-long-safe [v]
  (try
    (Long/parseLong (str v))
    (catch Exception _ nil)))

(defn- valid-person? [{:keys [name surname birthDate weight]}]
  (and (not (str/blank? (str name)))
       (not (str/blank? (str surname)))
       (not (str/blank? (str birthDate)))
       (some? weight)))

(defn- normalize-person-body [{:keys [name surname birthDate weight]}]
  {:name name
   :surname surname
   :date_of_birth birthDate
   :weight weight})

(defn list-persons [_]
  (response/ok (db/get-persons)))

(defn get-person [{{:keys [id]} :path-params}]
  (let [id' (parse-long-safe id)]
    (if-not id'
      (response/bad-request {:error "id inválido"})
      (if-let [person (db/get-person-by-id {:id id'})]
        (response/ok person)
        (response/not-found {:error "Persona no encontrada"})))))

(defn create-person [{{:keys [name surname birthDate weight] :as body} :body-params}]
  (if-not (valid-person? body)
    (response/bad-request
      {:error "Datos inválidos"
       :required ["name" "surname" "birthDate" "weight"]})
    (do
      (db/create-person! (normalize-person-body body))
      (response/created {:message "Persona creada correctamente"}))))

(defn update-person [{{:keys [id]} :path-params
                      {:keys [name surname birthDate weight] :as body} :body-params}]
  (let [id' (parse-long-safe id)]
    (cond
      (nil? id')
      (response/bad-request {:error "id inválido"})

      (not (valid-person? body))
      (response/bad-request
        {:error "Datos inválidos"
         :required ["name" "surname" "birthDate" "weight"]})

      (nil? (db/get-person-by-id {:id id'}))
      (response/not-found {:error "Persona no encontrada"})

      :else
      (do
        (db/update-person! (assoc (normalize-person-body body) :id id'))
        (response/ok {:message "Persona actualizada correctamente"})))))

(defn delete-person [{{:keys [id]} :path-params}]
  (let [id' (parse-long-safe id)]
    (if-not id'
      (response/bad-request {:error "id inválido"})
      (if-let [_ (db/get-person-by-id {:id id'})]
        (do
          (db/delete-person! {:id id'})
          (response/ok {:message "Persona eliminada correctamente"}))
        (response/not-found {:error "Persona no encontrada"})))))

(defn person-api-routes []
  ["/api/person"
   {:middleware [middleware/wrap-formats]}
   ["" {:get list-persons
        :post create-person}]
   ["/:id" {:get get-person
            :put update-person
            :delete delete-person}]])
