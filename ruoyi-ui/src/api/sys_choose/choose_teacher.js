
import request from '@/utils/request'

// 查询教师信息列表
export function listTeacher(query) {
    return request({
      url: '/choose/teacher/list',
      method: 'get',
      params: query
    })
  }
  
  // 选择老师
  export function choose(data) {
    return request({
      url: '/sys_choose/choose_teacher',
      method: 'post',
      params: data
    })
  }
  
  //获取选择的导师
  export function showChosen() {
    return request({
      url: '/sys_choose/choose_teacher/showChosen',
      method: 'get'
    })
  }

  export function deleteChosen(data) {
    return request({
      url: '/sys_choose/choose_teacher/deleteChosen',
      method: 'post',
      params: data
    })
  }