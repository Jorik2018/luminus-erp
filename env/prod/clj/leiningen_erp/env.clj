(ns leiningen-erp.env
  (:require [clojure.tools.logging :as log]))

(def defaults
  {:init
   (fn []
     (log/info "\n-=[leiningen-erp started successfully]=-"))
   :stop
   (fn []
     (log/info "\n-=[leiningen-erp has shut down successfully]=-"))
   :middleware identity})
