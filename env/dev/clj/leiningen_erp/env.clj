(ns leiningen-erp.env
  (:require
    [selmer.parser :as parser]
    [clojure.tools.logging :as log]
    [leiningen-erp.dev-middleware :refer [wrap-dev]]))

(def defaults
  {:init
   (fn []
     (parser/cache-off!)
     (log/info "\n-=[leiningen-erp started successfully using the development profile]=-"))
   :stop
   (fn []
     (log/info "\n-=[leiningen-erp has shut down successfully]=-"))
   :middleware wrap-dev})
