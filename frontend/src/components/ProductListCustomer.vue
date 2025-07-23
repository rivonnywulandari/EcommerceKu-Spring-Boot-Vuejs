<template>
  <div class="container my-4">
    <h3 class="text-center mb-4">Our Products</h3>

    <!-- Welcome & Action -->
    <div class="d-flex justify-content-between align-items-center mb-4">
      <div v-if="username">Welcome, <b>{{ username }}</b></div>
      <div>
        <!-- <button class="btn btn-primary me-2" @click="goToCart">My Cart</button> -->
      </div>
    </div>

    <!-- Daftar Produk -->
    <div class="row">
      <div class="col-4 mb-4" v-for="product in products" :key="product.id">
        <div class="card shadow-sm">
          <div class="row g-0">
            <!-- Gambar -->
            <div class="col-md-4">
              <img :src="product.imageUrl" class="img-fluid rounded-start w-100 h-100" style="object-fit: cover;" alt="Product Image">
            </div>

            <!-- Detail Produk -->
            <div class="col-md-8">
              <div class="card-body">
                <p class="card-title" style="text-align: left; font-weight: bold;">{{ product.name }}</p>
                <span class="badge rounded-pill bg-secondary" style="display: table; justify-content: flex-start;">{{ product.type }}</span>
                <p class="card-text mb-2" style="display: flex; justify-content: flex-start;">Rp{{ product.price.toLocaleString('id-ID') }}</p>

                <div class="input-group" style="max-width: 150px;">
                  <input type="number" v-model.number="product.quantity" min="1" class="form-control" placeholder="Qty">
                  <button class="btn btn-success" @click="addToCart(product)">Add</button>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>  
    </div>
  </div>
</template>

<script>
import axios from 'axios'

export default {
  name: 'ProductListCustomer',
  data() {
    return {
      products: [],
      username: 'Guest'
    }
  },
  methods: {
    fetchProducts() {
      axios.get('http://localhost:8080/api/products')
        .then(res => {
          this.products = res.data.map(product => ({
            ...product,
            imageUrl: `https://picsum.photos/id/${product.id}/200/300`,
            quantity: 1
          }))
        })
        .catch(err => console.error('Gagal mengambil produk:', err))
    },
    addToCart(product) {
      if (product.quantity < 1) {
        alert('Minimal 1 barang harus ditambahkan ke keranjang.')
        return
      }

      const cartItem = {
        product_id: product.id,
        name: product.name,
        type: product.type,
        price: product.price,
        quantity: product.quantity
      }

      let cart = JSON.parse(localStorage.getItem('cart')) || []
      cart.push(cartItem)
      localStorage.setItem('cart', JSON.stringify(cart))
      alert('Produk ditambahkan ke keranjang!')
    },
    goToCart() {
      this.$router.push('/cart')
    }
  },
  mounted() {
    this.fetchProducts()
  }
}
</script>

<style scoped>
.card {
  border: 1px solid #eee;
  border-radius: 10px;
}

.card-img {
  height: 100%;
  object-fit: cover;
}
</style>
