<template>
  <div class="w-full h-[100vh] flex items-center justify-center">
    <Loader v-if="isLoading" />
    <div v-motion-fade class="h-[80%] w-[80%] flex flex-col gap-4" v-else>
      <div class="text-md text-gray-800">
        Running node : <span class="text-primary">{{ nodeId }}</span>
      </div>
      <div class="text-2xl text-gray-800">Click Button to fetch Data</div>
      <div class="flex gap-4">
        <v-btn @click="showStudentsFunction()" color="primary">Students</v-btn>
        <v-btn @click="showSubjectsFunction()" color="primary">Subjects</v-btn>
      </div>

      <div
        v-if="showStudents || showSubjects"
        v-motion-fade
        class="text-2xl text-gray-800 transition-all duration-300 ease-in"
      >
        {{ showStudents ? 'Students' : 'Subjects' }}
      </div>
      <v-table
        v-motion-fade
        v-if="showStudents"
        height="80%"
        fixed-header
        class="rounded border min-h-[300px]"
      >
        <thead>
          <tr class="text-lg font-bold text-primary">
            <th class="text-left">#</th>
            <th class="text-left">Name</th>
            <th class="text-left">Email</th>
            <th class="text-left">Registration No.</th>
            <th class="text-left">Program</th>
          </tr>
        </thead>
        <tbody>
          <tr
            class="text-[15px] lg:text-lg"
            v-for="(student, index) in studentsData"
            :key="student.name"
          >
            <td class="font-semibold text-gray-700">
              {{ index + 1 }}
            </td>
            <td>{{ student.first_name }}&nbsp;{{ student.last_name }}</td>
            <td>
              {{ student.email }}
            </td>
            <td>{{ student.regNo }}</td>
            <td>{{ student.program }}</td>
          </tr>
        </tbody>
      </v-table>

      <v-table
        v-motion-fade
        v-if="showSubjects"
        height="80%"
        fixed-header
        class="rounded border min-h-[300px]"
      >
        <thead>
          <tr class="text-lg font-bold text-primary">
            <th class="text-left">#</th>
            <th class="text-left">Name</th>
            <th class="text-left">Code</th>
            <th class="text-left">Academic Year</th>
            <th class="text-left">Semester</th>
          </tr>
        </thead>
        <tbody>
          <tr
            class="text-[15px] lg:text-lg"
            v-for="(subject, index) in subjectsData"
            :key="subject.id"
          >
            <td>{{ index + 1 }}</td>
            <td class="font-semibold text-gray-700">
              {{ subject.name }}
            </td>
            <td>{{ subject.subjectCode }}</td>
            <td>{{ subject.academicYear }}</td>
            <td>{{ subject.semester }}</td>
          </tr>
        </tbody>
      </v-table>
    </div>
  </div>
</template>

<script setup>
import Loader from '@/components/Loader.vue'
import axios from 'axios'
import { ref, onMounted } from 'vue'

const isLoading = ref(true)
const showStudents = ref(false)
const showSubjects = ref(false)
const studentsData = ref([])
const subjectsData = ref([])

const showStudentsFunction = () => {
  showSubjects.value = false
  showStudents.value = true
}

const showSubjectsFunction = () => {
  showStudents.value = false
  showSubjects.value = true
}

const getStudents = async () => {
  await axios.get(`${import.meta.env.VITE_APP_BASE_URL}/api/v1/student/all`).then((res) => {
    studentsData.value = res.data
  })
}

const getSubjects = async () => {
  await axios.get(`${import.meta.env.VITE_APP_BASE_URL}/api/v1/subject/all`).then((res) => {
    subjectsData.value = res.data
  })
}

const nodeId = ref(null)

const getNodeId = async () => {
  try {
    const response = await fetch('/ping', { method: 'GET' })
    nodeId.value = response.headers.get('X-Node-ID') || 'Unknown'
  } catch (error) {
    console.error('Failed to get node ID:', error)
  }
}

onMounted(() => {
  getNodeId()

  getStudents()
  getSubjects()

  setInterval(() => {
    isLoading.value = false
  }, 2000)
})
</script>
