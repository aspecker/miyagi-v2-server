package miyagi.v2

import grails.gorm.transactions.Transactional

@Transactional
class PersonService {

    def save(String firstName, String lastName, String dateOfBirth, String address) {
        Person person = new Person(firstName: firstName, lastName: lastName, dateOfBirth: dateOfBirth, address: address)
        person.save()
        return person
    }

    @Transactional(readOnly = true)
    def count() {
//        return Person.list().groupBy {
//            it.dateOfBirth.month
//        }
        Integer[] monthCount = new Integer[12];
        Arrays.fill(monthCount, 0);
        Person.list().collect {
            Integer thisMonth = it.dateOfBirth.getMonth();
            monthCount[thisMonth]++;
        }
        return monthCount;
    }

    def filter(String firstName, String lastName, String startDate, String endDate) {
//        def interpolateString() = {}
        def c = Person.createCriteria()
//        def results = c.list {
//            like ("firstName", '%'+firstName+'%')
//            and like {"lastName", }
//        }
    }
}
