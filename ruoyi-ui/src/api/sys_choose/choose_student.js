
import request from '@/utils/request'

// 获取导师id和名字
export function getTeacherId() {
  return request({
    url: '/sys_choose/choose_student/getTeacherId',
    method: 'get'
  })
}

// 查询教师信息列表
export function listStudent(query) {
    return request({
      url: '/sys_choose/choose_student/list',
      method: 'get',
      params: query
    })
  }
  
  // 选择学生
  export function choose(data) {
    return request({
      url: '/sys_choose/choose_student',
      method: 'post',
      params: data
    })
  }
  
  //获取选择的导师
  export function showChosen() {
    return request({
      url: '/sys_choose/choose_student/showChosen',
      method: 'get'
    })
  }

  export function deleteChosen(data) {
    return request({
      url: '/sys_choose/choose_student/deleteChosen',
      method: 'post',
      params: data
    })
  }