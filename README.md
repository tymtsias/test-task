## REQUIREMENTS
* docker-ce 23.0.1
* sbt 1.7.2
* java 11

##  HOW TO RUN
if vstat is not available, use USE_REAL_VSTAT=false
### from sbt
1. export COOKIE='vstat_session=here_should_be_value;'
2. export USE_REAL_VSTAT=true/false
3. sbt run

### from docker
1. sbt docker:publishLocal
2. docker images | grep test-task
3. docker run -p 8080:8080 --env COOKIE='vstat_session=here_should_be_value;' --env USE_REAL_VSTAT=true/false test-task:your_tag_here

### available API 
http://0.0.0.0:8080/category/your_category
### available categories

* bank
* jewelry_store
* pet_store
* travel_insurance_company
* clothing_store
* energy_supplier
* car_dealer
* electronics_technology
* real_estate_agents
* furniture_store
* fitness_and_nutrition_service
* insurance_agency
* bedroom_furniture_store
* bicycle_store
* cosmetics_store
* activewear_store
* shoe_store
* electronics_store
* womens_clothing_store
* mortgage_broker
* garden_center
* mens_clothing_store
* appliance_store
* travel_agency