package miyagi.v2

import grails.testing.services.ServiceUnitTest
import spock.lang.Specification

class MapServiceSpec extends Specification implements ServiceUnitTest<MapService>{
    MapService mapService = new MapService()
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
            String expected = "Helloooo"
        when:
            def parsedString = mapService.parseQueryString(s)
        then:
            parsedString == expected
            printf("Parses punctuation test succeeded \n")
    }

    void "replaces spaces with +"() {
        given:
            String s = "123 Fake St Funkytown USA 12345"
            String expected = "123+Fake+St+Funkytown+USA+12345"
        when:
            def parseString = mapService.parseQueryString(s)
        then:
            parseString == expected
            printf("Parses spaces test succeeded \n")
    }

    void "gets results when using a valid address"() {
        given:
            String s = "1661 Pennsylvania Avenue NW, Washington, DC 20006"
        when:
            String mapResponse = mapService.calculateDistanceToGSHQ(s)
        then:
            mapResponse.contains("mi")
    }
    void "returns 404 when given a bad address"() {
        given:
            String s ="TOTALLYNOTREALADDRESSTOGETBADREQUEST"
        when:
            def mapResponse = mapService.calculateDistanceToGSHQ(s)
        then:
            mapResponse == 404

    }
}
