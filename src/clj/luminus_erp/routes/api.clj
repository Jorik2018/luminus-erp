(ns luminus-erp.routes.api
  (:require
    [ring.util.http-response :as response]
    [luminus-erp.middleware :as middleware]))

(defn get-health [_]
  (response/ok {:status "ok"}))

(defn get-clientes [_]
  (response/ok [{:id 1 :nombre "Juan"}
                {:id 2 :nombre "Ana"}]))

(defn create-cliente [{{:keys [nombre email]} :body-params}]
  (response/created {:mensaje "Cliente creado"
                     :cliente {:id 3
                               :nombre nombre
                               :email email}}))

(defn api-routes []
  ["/api"
   {:middleware [middleware/wrap-formats]}
   ["/health" {:get get-health}]
   ["/clientes"
    {:get get-clientes
     :post create-cliente}]])
