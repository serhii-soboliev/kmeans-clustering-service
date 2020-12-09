provider "azurerm" {
  features {}
}

module "backend_vm" {
  source = ".//backend_vm"
}

module "backend_container" {
  source = ".//backend_container"
}

module "frontend_container" {
  source = ".//frontend_container"
}