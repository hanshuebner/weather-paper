(ns weather-paper.world-weather-online
  (:require [clojure.java.io :as io]
            [clj-http.client :as http]))

(def api-key "ac658121f2b3fa57a12c400d6eb47")

(def base-url "http://api.worldweatheronline.com/free/v2/weather.ashx")

(defn query [location]
  (-> (http/get base-url
                {:throw-exceptions true
                 :as :json
                 :coerce :always
                 :query-params {:key api-key
                                :num_of_days 5
                                :format "json"
                                :q location}})
      :body
      :data))

(defn get-weather [location]
  (let [weather (query location)
        today (-> weather :weather first)
        {:keys [temp_C weatherCode]} (-> weather :current_condition first)]
    {:location location
     :temperature (Integer/parseInt temp_C)
     :temperature-min (Integer/parseInt (:mintempC today))
     :temperature-max (Integer/parseInt (:maxtempC today))
     :icon (io/resource (str "world-weather-online-icons/night/" weatherCode ".svg"))}))

