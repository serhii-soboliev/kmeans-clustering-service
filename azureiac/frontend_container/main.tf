resource "azurerm_resource_group" "kmeans_cs_frnt_cnt_rg" {
  name = "kmeans_cs_frnt_cnt_rg"
  location = "West Europe"
}

//resource "azurerm_container_registry" "kmeans_cr_container_registry" {
//  name                     = "kmeanscr"
//  resource_group_name      = azurerm_resource_group.kmeans_cs_frnt_cnt_rg.name
//  location                 = "West Europe"
//  sku                      = "Basic"
//  admin_enabled            = true
//}


resource "azurerm_container_group" "frontend_container" {
  name = "frontend_container"
  location = azurerm_resource_group.kmeans_cs_frnt_cnt_rg.location
  resource_group_name = azurerm_resource_group.kmeans_cs_frnt_cnt_rg.name
  ip_address_type = "public"
  dns_name_label = "kmeans-frontend"
  os_type = "Linux"
  image_registry_credential {
    password = var.container_registry_password
    server = var.container_registry_server
    username = var.container_registry_username
  }

  container {
    name = "kmeans-frontend"
    image = "kmeanscr.azurecr.io/kmeans/frontend"
    cpu = "0.5"
    memory = "1.5"

    ports {
      port = 8888
      protocol = "TCP"
    }
  }
}