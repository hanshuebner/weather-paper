(ns weather-paper.image
  (:require [clojure.string :as string]
            [tikkba.dom :as dom]
            [analemma.svg :as svg]
            [analemma.xml :as xml]
            [tikkba.transcoder :as transcoder])
  (:import org.apache.batik.dom.svg.SAXSVGDocumentFactory
           org.apache.batik.util.XMLResourceDescriptor))

(defn read-svg [filename]
  (-> (XMLResourceDescriptor/getXMLParserClassName)
      SAXSVGDocumentFactory.
      (.createDocument filename)))

(defn font [svg size]
  (svg/style svg
             :fill "#000000"
             :font-family "PT Sans"
             :font-size size))

(defn text [x y size fmt & args]
  (-> (svg/text (apply format fmt args))
      (xml/add-attrs :x x :y y)
      (font size)))

(defn make-weather-svg [{:keys [location
                                temperature icon
                                temperature-min temperature-max]}]
  (dom/svg-doc (svg/svg (-> (svg/rect -1 -1 177 265)
                        (svg/style :fill "white" :stroke "black" :stroke-width 1))
                    (text 3 23 "20px" (string/replace location #"\s*,.*" ""))
                    (text 63 73 "50px" "%2d°" temperature)
                    (text 150 50 "20px" "%2d°" temperature-min)
                    (text 150 73 "20px" "%2d°" temperature-max)
                    (-> (svg/image icon
                                   :width 50 :height 50)
                        (xml/add-attrs :x 2 :y 28)))))

(transcoder/to-png (make-weather-svg (weather-paper.world-weather-online/get-weather "Berlin, de"))
                   "/tmp/test.png"
                   {:width 265 :height 177})
