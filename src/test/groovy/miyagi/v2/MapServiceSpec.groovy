package miyagi.v2

import grails.testing.services.ServiceUnitTest
import spock.lang.Specification

class MapServiceSpec extends Specification implements ServiceUnitTest<MapService>{
    def MapService;
    def setup() {
    }

    def cleanup() {
    }

    //when:
    //given:
    //then:
    void "parses punctuation out of string"() {
        given:
            String s = "H!@e#%l^&*l*()o,."
            String expected = "Hello"
            MapService mapService = new MapService()
        when:
            def parsedString = mapService.parseQueryString(s)
        then:
            parsedString == expected
    }
}
