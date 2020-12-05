provider "azurerm" {
  features {}
}

module "backend" {
  source = ".//backend"
}