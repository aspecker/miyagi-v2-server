package miyagi.v2

import grails.converters.JSON

class PersonController {
    def personService

    def index() {
        render Person.list() as JSON
    }

    def save(String firstName, String lastName, String dateOfBirth, String address) {
        personService.save(firstName, lastName, dateOfBirth, address)
        render "bobok"
    }
}
