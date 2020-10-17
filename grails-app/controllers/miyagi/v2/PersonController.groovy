package miyagi.v2

import grails.converters.JSON

class PersonController {
    def personService
    def mapService

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
        def mapResponse = mapService.calculateDistanceToGSHQ(address)
        if (mapResponse == 403) {
            return render(status: 403, text: "Missing API Key.")
        }
        if (address == "" || address == null) {
            return render(status: 400, text: "Missing required fields: address.")
        }

        if (mapResponse == 404) {
            return render(status: 404, text: "Unable to connect to Maps server")
        }

        render(status: 200, text: mapResponse)
    }
}
