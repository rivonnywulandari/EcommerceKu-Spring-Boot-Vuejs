<template>
  <div class="container my-4">
    <h3 >Product List</h3>

    <!-- Form Tambah/Edit Produk -->
    <form @submit.prevent="submitProduct">
      <div class="row g-3 mb-3">
        <div class="col-md-4">
          <input v-model="newProduct.name" type="text" class="form-control" placeholder="Product Name" required />
        </div>
        <div class="col-md-3">
          <input v-model.number="newProduct.price" type="number" class="form-control" placeholder="Price" required />
        </div>
        <div class="col-md-3">
          <input v-model="newProduct.type" type="text" class="form-control" placeholder="Type" required />
        </div>
        <input v-model.number="newProduct.is_delete" type="hidden" value="1" required />
        <div class="col-md-2 d-flex gap-2">
          <button type="submit" class="btn btn-primary w-100">
            {{ isEditing ? 'Save' : 'Add' }}
          </button>
          <button type="button" class="btn btn-secondary" v-if="isEditing" @click="cancelEdit">Cancel</button>
        </div>
      </div>
    </form>

    <!-- Tabel Produk -->
    <div class="table-responsive">
      <table class="table table-bordered table-striped align-middle">
        <thead class="table-dark">
          <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Type</th>
            <th>Price</th>
            <!-- <th>is_delete</th> -->
            <th>Action</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="product in products" :key="product.id">
            <td>{{ product.id }}</td>
            <td>{{ product.name }}</td>
            <td>{{ product.type }}</td>
            <td>Rp{{ product.price.toLocaleString('id-ID') }}</td>
            <!-- <td>{{ product.is_delete }}</td> -->
            <td>
              <button class="btn btn-warning btn-sm me-2" @click="editProduct(product)">Edit</button>
              <button class="btn btn-danger btn-sm" @click="deleteProduct(product.id)">Delete</button>
            </td>
          </tr>
        </tbody>
      </table>
    </div>
  </div>
</template>


<script>
import axios from 'axios'

export default {
  name: 'ProductList',
  data() {
    return {
      products: [],
      newProduct: {
        name: '',
        price: 0,
        type: '',
        is_delete: 1
      }
    }
  },
  methods: {
  fetchProducts() {
    axios.get('http://localhost:8080/api/products')
      .then(response => {
        this.products = response.data;
      })
      .catch(error => {
        console.error('Gagal mengambil produk:', error);
      });
  },

  submitProduct() {
    if (this.isEditing) {
      // PUT (update)
      axios.put(`http://localhost:8080/api/products/${this.editProductId}`, this.newProduct)
        .then(() => {
          this.resetForm();
          this.fetchProducts();
        })
        .catch(error => {
          console.error('Gagal memperbarui produk:', error);
        });
    } else {
      // POST (tambah)
      axios.post('http://localhost:8080/api/products', this.newProduct)
        .then(() => {
          this.resetForm();
          this.fetchProducts();
        })
        .catch(error => {
          console.error('Gagal menambah produk:', error);
        });
    }
  },

  editProduct(product) {
    this.newProduct = { ...product }; // spread agar reactive
    this.editProductId = product.id;
    this.isEditing = true;
  },

  cancelEdit() {
    this.resetForm();
  },

  resetForm() {
    this.newProduct = {
      name: '',
      price: 0,
      type: '',
      is_delete: 1
    };
    this.editProductId = null;
    this.isEditing = false;
  },

  deleteProduct(id) {
    axios.put(`http://localhost:8080/api/products/${id}/soft-delete`)
      .then(() => this.fetchProducts())
      .catch(error => console.error('Gagal menghapus produk:', error));
  }
},
  mounted() {
    this.fetchProducts();
  }
}
</script>

<style scoped>
.container {
  max-width: 900px;
}

.product-list {
  /* max-width: 600px; */
  margin: 2rem auto;
  padding: 1rem;
  border: 1px solid #ccc;
  border-radius: 8px;
}
.product-form {
  display: flex;
  gap: 0.5rem;
  margin-bottom: 1rem;
}
input[type="text"],
input[type="number"] {
  flex: 1;
  padding: 0.5rem;
}
button {
  /* border: black solid 1px; */
  padding: 0.5rem 1rem;
}
ul {
  list-style: none;
  padding: 0;
}
li {
  margin-bottom: 0.5rem;
  display: flex;
  justify-content: space-between;
}
</style>
