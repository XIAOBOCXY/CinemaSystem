import Vue from 'vue'
import VueRouter from 'vue-router'
import Login from '@/views/Login.vue'
import Login2 from '@/views/Login2.vue'
import Login3 from '@/views/Login3.vue'
import Register from '@/views/Register.vue'
import Layout from '@/components/layout/Layout.vue'
import Layoutback from '@/components/layoutback/Layoutback.vue'
import Layoutservice from '@/components/layoutservice/Layoutservice.vue'
Vue.use(VueRouter)

const routes = [
  {
    path: '/login',
    name: 'login',
    component: Login
  },
  {
    path: '/register',
    name: 'register',
    component: Register
  },
  {
    path:'/',
    component:Layout,
    children:[
      {
        path:'',
        component:()=>import("@/views/Home")
      },
      {
        path:'/films',
        component:()=>import("@/views/Films")
      },
      {
        path:'/activity',
        component:()=>import("@/views/Activity")
      },
      {
        path:'/message',
        component:()=>import("@/views/Message")
      },
      {
        path:'/film',
        component:()=>import("@/views/film/Index.vue"),
        children:[
          {
          path:"detail",
          component:()=>import("@/views/film/Detail.vue"),
        },{
          path:"ticket",
          component:()=>import("@/views/film/Ticket.vue"),
        }]
      },
      {
        path:'/seat',
        component:()=>import("@/views/Seat")
      },
      {
        path:'/me',
        component:()=>import("@/views/me/Index.vue"),
        children:[
          {
          path:"cart",
          component:()=>import("@/views/me/Cart.vue"),
          },
          {
            path:"order",
            component:()=>import("@/views/me/Order.vue"),
          },
          {
            path:"setting",
            component:()=>import("@/views/me/Setting.vue"),
          }
        ]
      }         
    ]
  },
  {
    path: '/login2',
    name: 'login2',
    component: Login2
  },
  {
    path:'/back',
    component:Layoutback,
    children:[
      {
        path:'/',
        component:()=>import("@/views/back/moviemanage/Movielist.vue")
      },
      {
        path:"moviemanage/movielist",
        component:()=>import("@/views/back/moviemanage/Movielist.vue"),
      },
      {
        path:"moviemanage/moviechedule",
        component:()=>import("@/views/back/moviemanage/Moviechedule.vue"),
      },
      {
        path:"moviemanage/addmovie",
        component:()=>import("@/views/back/moviemanage/Addmovie.vue"),
      },
      {
        path:"moviemanage/postermanage",
        component:()=>import("@/views/back/moviemanage/Postermanage.vue"),
      },
      {
        path:"usermanage/userlist",
        component:()=>import("@/views/back/usermanage/Userlist.vue"),
      },
      {
        path:"ordermanage/orderlist",
        component:()=>import("@/views/back/ordermanage/Orderlist.vue"),
      },
      {
        path:"ordermanage/abnormalorder",
        component:()=>import("@/views/back/ordermanage/Abnormalorder.vue"),
      },
      {
        path:"workermanage/workerlist",
        component:()=>import("@/views/back/workermanage/Workerlist.vue"),
      },
      {
        path:"workermanage/addworker",
        component:()=>import("@/views/back/workermanage/Addworker.vue"),
      }
    ]
  },
  {
    path: '/login3',
    name: 'login3',
    component: Login3
  },
  // {
  //   path: '/register3',
  //   name: 'register3',
  //   component: Register3
  // },
  {
    path:'/service',
    component:Layoutservice,
    children:[
      {
        path:'/',
        component:()=>import("@/views/service/messarrangement.vue"),
      },
      {
        path:"messarrangement",
        component:()=>import("@/views/service/messarrangement.vue"),
      },
      {
        path:"activityarrangement",
        component:()=>import("@/views/service/activityarrangement.vue"),
      },
      {
        path:"infoarrangement",
        component:()=>import("@/views/service/infoarrangement.vue"),
      },
      {
        path:"mycomment",
        component:()=>import("@/views/service/mycomment.vue"),
      },
      {
        path:"setting",
        component:()=>import("@/views/service/setting.vue"),
      },
    ]
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
