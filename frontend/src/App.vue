<template>
  <div id="app">
    <!-- Navbar -->
    <nav class="navbar navbar-expand-lg navbar-dark bg-red">
      <div class="container">
        <a class="navbar-brand" href="#">EcommerceKu</a>
        <div class="ms-auto">
          <button class="btn btn-outline-light">Login</button>
        </div>
      </div>
    </nav>

    <!-- Tempat tampilnya halaman (seperti ProductList.vue) -->
    <router-view />
  </div>
</template>

<script>
import ProductList from './components/ProductList.vue'

export default {
  name: 'App',
  components: {
    ProductList
  }
}
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  text-align: center;
}

.bg-red{
  background-color: #D0011B;
  /* background-color: #42B549; */
}
</style>
