resource "azurerm_resource_group" "kmeans_cs_rg" {
  name = "kmenas_cs_rg"
  location = "West Europe"
}

resource "azurerm_virtual_network" "kmeans_cs_network" {
  name = "kmeans_cs_network"
  address_space = ["10.0.0.0/16"]
  location = "eastus"
  resource_group_name = azurerm_resource_group.kmeans_cs_rg.name
}