(ns weather-paper.image
  (:require [tikkba.dom :as dom]
            [analemma.svg :as svg]
            [analemma.xml :as xml]
            [tikkba.transcoder :as transcoder])
  (:import org.apache.batik.dom.svg.SAXSVGDocumentFactory
           org.apache.batik.util.XMLResourceDescriptor))

(defn read-svg [filename]
  (-> (XMLResourceDescriptor/getXMLParserClassName)
      SAXSVGDocumentFactory.
      (.createDocument filename)))

(defn make-weather-svg []
  (dom/svg-doc (svg (-> (svg/rect -1 -1 177 265)
                        (svg/style :fill "white" :stroke "black" :stroke-width 1))
                    (-> (svg/text "Berlin")
                        (xml/add-attrs :x 3 :y 23)
                        (svg/style :fill "#000000"
                                   :font-family "PT Sans"
                                   :font-size "20px"))
                    (-> (svg/text "19°C")
                        (xml/add-attrs :x 63 :y 73)
                        (svg/style :fill "#000000"
                                   :font-family "PT Sans"
                                   :font-size "50px"))
                    (-> (svg/image "file:///Users/hans/hans.huebner@gmail.com/MAm-WeatherIcons-MS01Be/icons/SVGs/wsymbol_0023_cloudy_with_heavy_hail.svg"
                                   :width 50 :height 50)
                        (xml/add-attrs :x 2 :y 25)))))

(transcoder/to-png (make-weather-svg)
                   "/tmp/test.png"
                   {:width 265 :height 177})
