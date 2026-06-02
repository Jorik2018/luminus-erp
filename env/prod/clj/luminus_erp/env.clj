(ns luminus-erp.env
  (:require [clojure.tools.logging :as log]))

(def defaults
  {:init
   (fn []
     (log/info "\n-=[luminus-erp started successfully]=-"))
   :stop
   (fn []
     (log/info "\n-=[luminus-erp has shut down successfully]=-"))
   :middleware identity})
