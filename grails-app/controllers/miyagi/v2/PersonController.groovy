package miyagi.v2

import grails.converters.JSON

class PersonController {
    def personService

    def index() {
        render Person.listOrderByLastName() as JSON
    }

    def save(String firstName, String lastName, String dateOfBirth, String address) {
        if (firstName == "" || lastName == "" || dateOfBirth == "" || address == "") {
            return render(status: 400, text: "Missing one or more required fields.")
        }
        personService.save(firstName, lastName, dateOfBirth, address)
        render "Success"
    }

    def count() {
        render personService.count()
    }

    def filter(String firstName, String lastName, String startDate, String endDate) {
        if (firstName == "" && lastName == "" && startDate == "" && endDate == "") {
            return render(status: 400, text: "Must provide at least one field to filter on.")
        }
        render personService.filter(firstName, lastName, startDate, endDate) as JSON
    }

    def calculateDistance(String address) {
        if (address == "") {
            return render(status: 400, text: "Address must be provided");
        }
        return MapService.calculateDistance(address)
    }
}
