package miyagi.v2

import grails.converters.JSON

class PersonController {
    def personService

    def index() {
        render Person.listOrderByLastName() as JSON
    }

    def save(String firstName, String lastName, String dateOfBirth, String address) {
        def req = request
        personService.save(firstName, lastName, dateOfBirth, address)
        render "Success"
    }

    def count() {
        render personService.count()
    }

    def filter(String firstName, String lastName, String startDate, String endDate) {
        render personService.filter(firstName, lastName, startDate, endDate) as JSON
    }
}
