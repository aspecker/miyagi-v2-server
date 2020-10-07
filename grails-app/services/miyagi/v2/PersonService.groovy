package miyagi.v2

import grails.gorm.transactions.Transactional

class PersonService {

    @Transactional
    def save(String firstName, String lastName, String dateOfBirth, String address) {
        Person person = new Person(firstName: firstName, lastName: lastName, dateOfBirth: dateOfBirth, address: address)
        person.save()
        return person
    }
}
