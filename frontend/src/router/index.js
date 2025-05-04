import LandingLayout from '@/views/landing/LandingLayout.vue'

import { createRouter, createWebHistory } from 'vue-router'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'Home',
      component: LandingLayout,
    },
  ],
})

export default router
