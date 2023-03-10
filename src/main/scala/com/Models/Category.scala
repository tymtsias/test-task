package com.Models

sealed trait Category {
  val name: String
}

object Category{

  object Bank extends Category {
    override val name: String = "bank"
  }
  object Jewelery extends Category {
    override val name: String = "jewelry_store"
  }
  object Pet extends Category {
    override val name: String = "pet_store"
  }
  object Travel extends Category {
    override val name: String = "travel_insurance_company"
  }
  object Clothing extends Category {
    override val name: String = "clothing_store"
  }
  object Energy extends Category {
    override val name: String = "energy_supplier"
  }
  object Car extends Category {
    override val name: String = "car_dealer"
  }
  object ElectronicsTechnology extends Category {
    override val name: String = "electronics_technology"
  }
  object RealEstate extends Category {
    override val name: String = "real_estate_agents"
  }
  object Furniture extends Category {
    override val name: String = "furniture_store"
  }
  object FitnessAndNutrition extends Category {
    override val name: String = "fitness_and_nutrition_service"
  }
  object Insurance extends Category {
    override val name: String = "insurance_agency"
  }
  object BedroomFurniture extends Category {
    override val name: String = "bedroom_furniture_store"
  }
  object Bicycle extends Category {
    override val name: String = "bicycle_store"
  }
  object Cosmetics extends Category {
    override val name: String = "cosmetics_store"
  }
  object Activewear extends Category {
    override val name: String = "activewear_store"
  }
  object Shoe extends Category {
    override val name: String = "shoe_store"
  }
  object Electronics extends Category {
    override val name: String = "electronics_store"
  }
  object WomensClothing extends Category {
    override val name: String = "womens_clothing_store"
  }
  object MortgageBroker extends Category {
    override val name: String = "mortgage_broker"
  }
  object Garden extends Category {
    override val name: String = "garden_center"
  }
  object MensClothing extends Category {
    override val name: String = "mens_clothing_store"
  }
  object Appliance extends Category {
    override val name: String = "appliance_store"
  }
  object TravelAgency extends Category {
    override val name: String = "travel_agency"
  }
}
