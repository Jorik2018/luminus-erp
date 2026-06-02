(ns luminus-erp.migrations
  (:require
    [migratus.core :as migratus]
    [mount.core :refer [defstate]]
    [luminus-erp.env :refer [env]]))

(defn migration-config []
  {:store :database
   :migration-dir "migrations"
   :db {:jdbcUrl (:database-url env)}})

(defstate migrate-db
  :start
  (do
    (println "Ejecutando migraciones...")
    (migratus/migrate (migration-config))
    (println "Migraciones completadas")
    true)
  :stop true)
