<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript"
	src="<%=basePath%>resources/js/strophe.js"></script>
<script type="text/javascript"
	src="<%=basePath%>resources/js/websdk-1.1.2.js"></script>
<script type="text/javascript"
	src="<%=basePath%>resources/js/webim.config.js"></script>
<script type="text/javascript"
	src="<%=basePath%>resources/js/jquery-1.9.1.min.js"></script>
<script type="text/javascript" 
	src="<%=basePath%>resources/js/websdk.shim.js"></script>
<title>Insert title here</title>
</head>
<body>
<span>login  + </span>
<span>${username},</span>
<span>${password}</span>

	
	<p>------------------------------------------------------</p>
	注册:
	<br> 昵称：<input type="text" name="nicknames" id="nns" /> 账号：
	<input type="text" name="usernames" id="uns" /> 密码：
	<input type="password" name="password" id="pwds">
	<button onclick="sign()">注册</button>
	<p>------------------------------------------------------</p>

	登录:
	<br> 账号：
	<input type="text" name="usernamel" id="unl" /> 密码：
	<input type="password" name="passwordl" id="pwdl">
	<button onclick="login()">登录</button>

	<p>------------------------------------------------------</p>
	<button onclick="close()">退出</button>
	<button onclick="Btntt()">跳转</button>
	<p>------------------------------------------------------</p>
	
	添加好友:
	<br> 好友名称：
	<input type="text" name="usernamea" id="una" />
	<button onclick="addFirend()">添加</button>

	<p>------------------------------------------------------</p>
	
		
	发送消息:<br> 
	好友名称：<input type="text" name="usernamem" id="unm" />
	消息内容：<input type="text" name="msgBody" id="msgBody" />
	<button onclick="sendMsg()">发送</button>

	<p>------------------------------------------------------</p>
</body>

<script type="text/javascript">
var token =null;
//创建连接
var conn = new WebIM.connection({
    https: WebIM.config.https,
    url: WebIM.config.xmppURL,
    isAutoLogin: WebIM.config.isAutoLogin,
    isMultiLoginSessions: WebIM.config.isMultiLoginSessions
});

//根据用户名/密码/昵称注册环信 Web IM:
function sign(){
	var nns= $('#nns').val();
	var uns = $('#uns').val();
	var pwds = $('#pwds').val();
	console.log("nns:"+nns+" uns:"+uns+"pwds:"+pwds);
	var options = { 
			  username: uns,
			  password: pwds,
			  nickname: nns,
			  appKey: "1119160928115744#boy",
			  success: function () {alert("注册成功"); },  
			  error: function () { alert("注册失败");}, 
			  apiUrl: WebIM.config.apiURL
	}; 
			   
	WebIM.utils.registerUser(options);
};

//根据用户名/密码登录环信 Web IM:
function login(){
	var uns = $('#unl').val();
	var pwds = $('#pwdl').val();
	console.log("uns:"+uns+" pwds:"+pwds);

	var options = { 
			  apiUrl: WebIM.config.apiURL,
			  user: uns,
			  pwd: pwds,
			  appKey: "posun#citysafety"
			};  
			conn.open(options);
};

//退出
function close(){
	conn.close();
}





//获取Token
function getToken(){
	$.post("https://a1.easemob.com/1119160928115744/boy/token", 
			{"grant_type": "client_credentials","client_id": "YXA6JmTFAIWNEeaQ5LG7Zc3H6g","client_secret": "YXA6Hxop4s8bnwYaLf1bZTPLPJaTFIk"},
        function(data){
			console.log("data.Token = "+ data.access_token);
			alert("data.Token = "+ data.access_token);
		}, "json");
};


//添加好友 
function addFirend(){
	var una = $('#una').val();
	
	conn.subscribe({
		  to: una,
		  message: "加个好友呗!"
		});
	console.log('una='+una);
};


//收到联系人订阅请求的处理方法，具体的type值所对应的值请参考xmpp协议规范
var handlePresence = function ( e ) {
	if (e.type == 'subscribe') {
			//若e.status中含有[resp:true],则表示为对方同意好友后反向添加自己为好友的消息，demo中发现此类消息，默认同意操作，完成双方互为好友；如果不含有[resp:true]，则表示为正常的对方请求添加自己为好友的申请消息。
			if (e && e.status == '[resp:true]') {
				alert("新增好友一枚！");
				return;
			} else {
				var se = confirm( e.from + ":"+e.status);
				if (se == true) {
					conn.subscribed({
						to : e.to,
						message : "[resp:true]"
					});
					conn.subscribe({
						to : e.from,
						message : "[resp:true]"
					});
				}else{
					conn.unsubscribed({
						to : e.from,
						message : "拒绝添加您为好友！"
					});
				}
			}
		}

		//(发送者允许接收者接收他们的出席信息)，即别人同意你加他为好友
		if (e.type == 'subscribed') {
			alert("新增好友一枚！");
		}

		//（发送者取消订阅另一个实体的出席信息）,即删除现有好友
		if (e.type == 'unsubscribe') {

		}

		//（订阅者的请求被拒绝或以前的订阅被取消），即对方单向的删除了好友
		if (e.type == 'unsubscribed') {
			alert(e.to+":"+e.status);
		}
	};
	

	function Btntt() {
		 location.href='<%=basePath%>demo'; 
	};
	
	function sendMsg(){
		var id = conn.getUniqueId();//生成本地消息id
		var msg = new WebIM.message('txt', id);//创建文本消息
		var unm = $('#unm').val();
		var msgBody = $('#msgBody').val();
		
		console.log('id='+id);
		console.log('msg='+msg);
		console.log('unm='+unm);
		console.log('msgBody='+msgBody);
		msg.set({
		  msg: msgBody,
		  to: unm,
		  success: function (id,serverMsgId ) {
			  alert('发送成功！');
			  var params = {
			        "imId" : id,
			        "groupId" :"",
			        "fromId": unm,
			        "content": msgBody
			  };
			 
			  $.ajax({
	                url: "<%=basePath%>imTemporaryRecordset/saveFromId",
	                type: "post",
	                data:"str="+JSON.stringify(params),
	                dataType: "json",
	                success: function (data) {
	                    console.init(data);
	                }
	            });
			  
		  }//消息发送成功回调   
		}); 
		
/* 		if (type == 'groupchat') { 
			  msg.setGroup("groupchat");
			} */
		console.log(msg);
		conn.send(msg.body);
	}
	
	   //回调
    conn.listen({
        onOpened: function ( message ) {          //连接成功回调
            //如果isAutoLogin设置为false，那么必须手动设置上线，否则无法收消息
            conn.setPresence();
        },
        onClosed: function ( message ) {},         //连接关闭回调
        onTextMessage: function ( message ) {
            console.log('onTextMessage');
            if(message.type = 'chat'){
                var html1 = "<div><span>&nbsp;"+message.from+":"+message.data+"</span></div>";
                $('#msgBody').val('');
                $('#contentView').append(html1);
            }
        },    //收到文本消息
        onEmojiMessage: function ( message ) {
            console.log('onEmojiMessage');
        },   //收到表情消息
        onPictureMessage: function ( message ) {
            console.log('onPictureMessage');
        }, //收到图片消息
        onCmdMessage: function ( message ) {console.log('onCmdMessage');},     //收到命令消息
        onAudioMessage: function ( message ) {console.log('onAudioMessage:');},   //收到音频消息
        onLocationMessage: function ( message ) {console.log('onLocationMessage');},//收到位置消息
        onFileMessage: function ( message ) {console.log('onFileMessage');},    //收到文件消息
        onVideoMessage: function ( message ) {console.log('onVideoMessage');},   //收到视频消息
        onPresence: function ( message ) {
            console.log('onPresence');
        },       //收到联系人订阅请求、处理群组、聊天室被踢解散等消息
        onRoster: function ( message ) {},         //处理好友申请
        onInviteMessage: function ( message ) {},  //处理群组邀请
        onOnline: function () {console.log('onOnline');},                  //本机网络连接成功
        onOffline: function () {console.log('onOffline');},                 //本机网络掉线
        onError: function ( message ) {
            console.log('message'+message);}           //失败回调
    });
</script>
</html>