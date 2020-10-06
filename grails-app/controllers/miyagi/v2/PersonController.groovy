package miyagi.v2

import grails.converters.JSON

class PersonController {

    def index() {
        render Person.list() as JSON
    }
}
