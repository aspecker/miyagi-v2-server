package miyagi.v2

import grails.gorm.transactions.Transactional

@Transactional
class MapService {

    def calculateDistance(String address) {
        return address
    }
}
