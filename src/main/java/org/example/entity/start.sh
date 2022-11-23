#!/bin/bash
#启服脚本(外网使用,跳过拉取代码和编译的过程)

INSTALL_PATH="/data/opt"
GLOBAL_SERVER_RUNTIME_PATH="${INSTALL_PATH}"/global
GATEWAY_SERVER_RUNTIME_PATH="${INSTALL_PATH}"/gateway
GAME_SERVER_RUNTIME_PATH="${INSTALL_PATH}"/game
UNION_SERVER_RUNTIME_PATH="${INSTALL_PATH}"/union
MAIL_SERVER_RUNTIME_PATH="${INSTALL_PATH}"/mail
CROSS_SERVER_RUNTIME_PATH="${INSTALL_PATH}"/cross
CHAT_SERVER_RUNTIME_PATH="${INSTALL_PATH}"/chat
TRANSLATE_SERVER_RUNTIME_PATH="${INSTALL_PATH}"/translate
ADMIN_SERVER_RUNTIME_PATH="${INSTALL_PATH}"/admin
STRATEGY_SERVER_RUNTIME_PATH="${INSTALL_PATH}"/strategy
RESOURCE_PATH="${INSTALL_PATH}"/resource

function startGlobalServer() {
  if [ -d "${GLOBAL_SERVER_RUNTIME_PATH}" ]; then
    echo "正在启动Global服务..."
    cd ${GLOBAL_SERVER_RUNTIME_PATH} && sh boot-service restart && cd - || exit
  fi
}

function stopGlobalServer() {
  if [ -d "${GLOBAL_SERVER_RUNTIME_PATH}" ]; then
    echo "正在关闭Global服务..."
    cd ${GLOBAL_SERVER_RUNTIME_PATH} && sh boot-service stop && cd - || exit
  fi
}

function startStrategyServer() {
  if [ -d "${STRATEGY_SERVER_RUNTIME_PATH}" ]; then
    echo "正在启动Strategy服务..."
    cd ${STRATEGY_SERVER_RUNTIME_PATH} && sh boot-service restart && cd - || exit
  fi
}

function stopStrategyServer() {
  if [ -d "${STRATEGY_SERVER_RUNTIME_PATH}" ]; then
    echo "正在关闭Strategy服务..."
    cd ${STRATEGY_SERVER_RUNTIME_PATH} && sh boot-service stop && cd - || exit
  fi
}

function startGatewayServer() {
  if [ -d "${GATEWAY_SERVER_RUNTIME_PATH}" ]; then
    echo "正在启动Gateway服务..."
    cd ${GATEWAY_SERVER_RUNTIME_PATH} && sh jsvc-service restart && cd - || exit
  fi
}

function stopGatewayServer() {
  if [ -d "${GATEWAY_SERVER_RUNTIME_PATH}" ]; then
    echo "正在关闭Gateway服务..."
    cd ${GATEWAY_SERVER_RUNTIME_PATH} && sh jsvc-service stop && cd - || exit
  fi
}

function startUnionServer() {
  if [ -d "${UNION_SERVER_RUNTIME_PATH}" ]; then
    echo "正在启动联盟服务..."
    cd ${UNION_SERVER_RUNTIME_PATH} && sh jsvc-service restart && cd - || exit
  fi
}

function stopUnionServer() {
  if [ -d "${UNION_SERVER_RUNTIME_PATH}" ]; then
    echo "正在关闭联盟服务..."
    cd ${UNION_SERVER_RUNTIME_PATH} && sh jsvc-service stop && cd - || exit
  fi
}

function startMailServer() {
  if [ -d "${UNION_SERVER_RUNTIME_PATH}" ]; then
    echo "正在启动邮件服务..."
    cd ${MAIL_SERVER_RUNTIME_PATH} && sh jsvc-service restart && cd - || exit
  fi
}

function stopMailServer() {
  if [ -d "${MAIL_SERVER_RUNTIME_PATH}" ]; then
    echo "正在关闭邮件服务..."
    cd ${MAIL_SERVER_RUNTIME_PATH} && sh jsvc-service stop && cd - || exit
  fi
}

function startTranslateServer() {
  if [ -d "${TRANSLATE_SERVER_RUNTIME_PATH}" ]; then
    echo "正在启动translate服务..."
    cd ${TRANSLATE_SERVER_RUNTIME_PATH} && sh jsvc-service restart && cd - || exit
  fi
}

function stopTranslateServer() {
  if [ -d "${TRANSLATE_SERVER_RUNTIME_PATH}" ]; then
    echo "正在关闭translate服务..."
    cd ${TRANSLATE_SERVER_RUNTIME_PATH} && sh jsvc-service stop && cd - || exit
  fi
}

function startChatServer() {
  if [ -d "${CHAT_SERVER_RUNTIME_PATH}" ]; then
    echo "正在启动聊天服务..."
    cd ${CHAT_SERVER_RUNTIME_PATH} && sh jsvc-service restart && cd - || exit
  fi
}

function stopChatServer() {
  if [ -d "${CHAT_SERVER_RUNTIME_PATH}" ]; then
    echo "正在关闭聊天服务..."
    cd ${CHAT_SERVER_RUNTIME_PATH} && sh jsvc-service stop && cd - || exit
  fi
}

function startCrossServer() {
  if [ -d "${CROSS_SERVER_RUNTIME_PATH}" ]; then
    echo "正在启动Cross服务..."
    cd ${CROSS_SERVER_RUNTIME_PATH} && sh jsvc-service restart && cd - || exit
  fi
}

function stopCrossServer() {
  if [ -d "${CROSS_SERVER_RUNTIME_PATH}" ]; then
    echo "正在关闭Cross服务..."
    cd ${CROSS_SERVER_RUNTIME_PATH} && sh jsvc-service stop && cd - || exit
  fi
}

function startGameServer() {
  if [ -d "${GAME_SERVER_RUNTIME_PATH}" ]; then
    echo "正在启动Game服务..."
    cd ${GAME_SERVER_RUNTIME_PATH} && sh jsvc-service restart && cd - || exit
  fi
}

function stopGameServer() {
  if [ -d "${GAME_SERVER_RUNTIME_PATH}" ]; then
    echo "正在关闭Game服务..."
    cd ${GAME_SERVER_RUNTIME_PATH} && sh jsvc-service stop && cd - || exit
  fi
}

function startAdminServer() {
  if [ -d "${ADMIN_SERVER_RUNTIME_PATH}" ]; then
    echo "正在启动Admin服务..."
    cd ${ADMIN_SERVER_RUNTIME_PATH} && sh boot-service restart && cd - || exit
  fi
}

function stopAdminServer() {
  if [ -d "${ADMIN_SERVER_RUNTIME_PATH}" ]; then
    echo "正在关闭Admin服务..."
    cd ${ADMIN_SERVER_RUNTIME_PATH} && sh boot-service stop && cd - || exit
  fi
}

function startAll() {
  echo "-------- 启动全部服务... ---------"
  startGlobalServer
  startAdminServer
  sleep 5
  startStrategyServer
  startGatewayServer
  startCrossServer
  startTranslateServer
  startUnionServer
  startChatServer
  startMailServer
  startGameServer
  echo "-------- 启动全部服务完成... ---------"
}

function stopAll() {
  echo "-------- 停止全部服务... ---------"
  stopGameServer
  stopMailServer
  stopChatServer
  stopUnionServer
  stopCrossServer
  stopGatewayServer
  stopTranslateServer
  stopAdminServer
  stopStrategyServer
  stopGlobalServer
  echo "-------- 停止全部服务完成... ---------"
}

function upgrade() {
  stopAll
  svn update "${INSTALL_PATH}"
  mkdir -p ${GAME_SERVER_RUNTIME_PATH}/resource
  mkdir -p ${UNION_SERVER_RUNTIME_PATH}/resource
  mkdir -p ${MAIL_SERVER_RUNTIME_PATH}/resource
  mkdir -p ${CROSS_SERVER_RUNTIME_PATH}/resource
  rm -rf ${GAME_SERVER_RUNTIME_PATH}/resource/* && cp "${RESOURCE_PATH}"/*.json ${GAME_SERVER_RUNTIME_PATH}/resource
  rm -rf ${UNION_SERVER_RUNTIME_PATH}/resource/* && cp "${RESOURCE_PATH}"/*.json ${UNION_SERVER_RUNTIME_PATH}/resource
  rm -rf ${MAIL_SERVER_RUNTIME_PATH}/resource/* && cp "${RESOURCE_PATH}"/*.json ${MAIL_SERVER_RUNTIME_PATH}/resource
  rm -rf ${CROSS_SERVER_RUNTIME_PATH}/resource/* && cp "${RESOURCE_PATH}"/*.json ${CROSS_SERVER_RUNTIME_PATH}/resource
  startAll
}

function menu() {
  echo "###############  MENU  ################"
  echo "# 101.部署Global服务"
  echo "# 102.部署Gateway服务"
  echo "# 103.部署联盟服务"
  echo "# 104.部署Mail服务"
  echo "# 105.部署Chat服务"
  echo "# 106.部署Game服务"
  echo "# 107.部署translate服务"
  echo "# 108.部署admin服务"
  echo "# 109.部署Cross服务"
  echo "# 110.部署strategy服务"
  echo "#######################################"
  echo "# 301.停止Global服务"
  echo "# 302.停止Gateway服务"
  echo "# 303.停止Union服务"
  echo "# 304.停止Mail服务"
  echo "# 305.停止Chat服务"
  echo "# 306.停止Game服务"
  echo "# 307.停止Translate服务"
  echo "# 308.停止Admin服务"
  echo "# 309.停止Cross服务"
  echo "# 310.停止strategy服务"
  echo "#######################################"
  echo "# 666.部署全部服务"
  echo "# 777.停止全部服务"
  echo "# 000.更新服务器"
  echo "#######################################"
  echo -e "请选择:"

  if [ -z "$choice" ]; then
    read -r choice
  fi
  case $choice in
  101) startGlobalServer ;;
  102) startGatewayServer ;;
  103) startUnionServer ;;
  104) startMailServer ;;
  105) startChatServer ;;
  106) startGameServer ;;
  107) startTranslateServer ;;
  108) startAdminServer ;;
  109) startCrossServer ;;
  110) startStrategyServer ;;
  301) stopGlobalServer ;;
  302) stopGatewayServer ;;
  303) stopUnionServer ;;
  304) stopMailServer ;;
  305) stopChatServer ;;
  306) stopGameServer ;;
  307) stopTranslateServer ;;
  308) stopAdminServer ;;
  309) stopCrossServer ;;
  310) stopStrategyServer ;;
  666)
    startAll
    ;;
  777) stopAll ;;
  000) upgrade ;;
  esac
}

#set -x
#set -e
choice=$1
menu
