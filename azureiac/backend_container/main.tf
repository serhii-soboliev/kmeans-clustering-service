resource "azurerm_resource_group" "kmeans_cs_cnt_rg" {
  name = "kmenas_cs_cnt_rg"
  location = "West Europe"
}

resource "azurerm_container_registry" "kmeans_cr_container_registry" {
  name                     = "kmeanscr"
  resource_group_name      = azurerm_resource_group.kmeans_cs_cnt_rg.name
  location                 = "West Europe"
  sku                      = "Basic"
}