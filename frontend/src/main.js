import './assets/main.css'
import '@mdi/font/css/materialdesignicons.css'
import '@fortawesome/fontawesome-free/css/all.css'
import { MotionPlugin } from '@vueuse/motion'

import { VDateInput } from 'vuetify/labs/VDateInput'

import { fa } from 'vuetify/iconsets/fa'
import { aliases, mdi } from 'vuetify/lib/iconsets/mdi'

import { createApp } from 'vue'
import { createPinia } from 'pinia'

import App from './App.vue'
import router from './router'

import 'vuetify/styles'
import { createVuetify } from 'vuetify'
import * as defaultComponents from 'vuetify/components'
import * as directives from 'vuetify/directives'

const app = createApp(App)

const vuetify = createVuetify({
  icons: {
    defaultSet: 'mdi',
    aliases,
    sets: {
      mdi,
      fa,
    },
  },
  components: {
    ...defaultComponents,
    VDateInput,
  },

  directives,
})

app.use(vuetify)
app.use(MotionPlugin)
app.use(createPinia())
app.use(router)

app.mount('#app')
