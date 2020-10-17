package miyagi.v2

import groovy.json.JsonSlurper

class MapService {
    def gsHQAddress = "1725 Desales St. NW, Washington, DC 20036"
    def baseMapsUrl = "https://maps.googleapis.com/maps/api/directions"

    def calculateDistanceToGSHQ(String address) {
        def env = System.getenv()
        def API_KEY = env['MAPS_TEST_API_KEY']
        if (API_KEY == null) {
            return 403
        }
        String targetUrl = "/json?origin=Charlotte&destination=Richmond&key="+API_KEY;
        def rawResponseString = new URL(baseMapsUrl + targetUrl).getText()
        def slurper = new JsonSlurper()
        def response = slurper.parseText(rawResponseString)
        return response?.routes?.getAt(0)?.legs?.getAt(0)?.distance?.text ?: 404
    }
}
