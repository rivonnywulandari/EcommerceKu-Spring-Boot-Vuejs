import { createRouter, createWebHistory } from 'vue-router'
import ProductList from '../components/ProductList.vue'
import ProductListCustomer from '../components/ProductListCustomer.vue'

const routes = [
  { 
    path: '/', 
    name: 'ProductList', 
    component: ProductList 
  },
  { 
    path: '/buy', 
    name: 'ProductListCustomer', 
    component: ProductListCustomer 
  }
]


const router = createRouter({
  history: createWebHistory(),
  routes
})

export default router
