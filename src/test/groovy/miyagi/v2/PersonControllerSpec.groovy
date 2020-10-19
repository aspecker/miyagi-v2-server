package miyagi.v2

import grails.testing.web.controllers.ControllerUnitTest
import spock.lang.Specification

class PersonControllerSpec extends Specification implements ControllerUnitTest<PersonController> {
    PersonController personController = new PersonController()
    def setup() {
    }

    def cleanup() {
    }

    void "gets 400 on null address"() {
        given:
            String s = null
        when:
            personController.calculateDistance(s)
        then:
            response["status"] == 400
    }

    void "gets 400 on empty string address"() {
        given:
        String s = ""
        when:
        personController.calculateDistance(s)
        then:
        response["status"] == 400
    }
}
