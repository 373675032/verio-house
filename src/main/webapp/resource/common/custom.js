/**
 * APP中文名称
 * @returns {string}
 */
function APPLICATION_CN_NAME() {
    return "租房通";
}

/**
 * APP英文名称
 * @returns {string}
 */
function APPLICATION_EN_NAME() {
    return "verio-house";
}

/**
 * 注册
 */
function register() {
    let name = $.trim($("#register-name").val());
    let sex = $.trim($("#register-sex").val());
    let email = $.trim($("#register-email").val());
    let phone = $.trim($("#register-phone").val());
    let location = $.trim($("#register-location").val());
    let password = $.trim($("#register-password").val());
    let role = $.trim($("#register-role").val());

    if (!name) {
        layer.msg("姓名不能为空");
        return;
    }
    if (!userNameReg(name)) {
        layer.msg("用户名格式错误，支持中文英文");
        return
    }
    if (!email) {
        layer.msg("邮箱不能为空");
        return;
    }
    if (!emailReg(email)) {
        layer.msg("请输入正确的邮箱格式");
        return;
    }
    if (!phone) {
        layer.msg("手机号不能为空");
        return;
    }
    if (!password) {
        layer.msg("密码不能为空");
        return;
    }
    if (!userPasswordReg(password)) {
        layer.msg("密码请输入8-16位字符，支持英文数字下划线");
        return;
    }

    $.ajax({
        type: "POST",
        url: "user/register",
        data: {
            name: name,
            email: email,
            phone: phone,
            location: location,
            sex: sex,
            role: role,
            password: password
        },
        dataType: "json",
        success: function (data) {
            console.log(data);
            layer.msg(data['message']);
            if (data['code'] === "EXIST_NAME") {
                shake("#register-name")
            } else if (data['code'] === "EXIST_EMAIL") {
                shake("#register-email")
            } else if (data['code'] === "EXIST_PHONE") {
                shake("#register-phone")
            } else {
                setTimeout('reload()', 2000)
            }
        }
    });
}

/**
 * 登录
 */
function login() {
    let email = $.trim($("#login-email").val());
    let password = $.trim($("#login-password").val());

    if (!email) {
        layer.msg("邮箱不能为空");
        return;
    }
    if (!emailReg(email)) {
        layer.msg("请输入正确的邮箱格式");
        return;
    }
    if (!password) {
        layer.msg("密码不能为空");
        return;
    }
    if (!userPasswordReg(password)) {
        layer.msg("密码请输入8-16位字符，支持英文数字下划线");
        return;
    }

    $.ajax({
        type: "POST",
        url: "user/login",
        data: {
            email: email,
            password: password
        },
        dataType: "json",
        success: function (data) {
            console.log(data);
            layer.msg(data['message']);
            if (data['code'] === "NOT_FOUND") {
                shake("#login-email")
            } else if (data['code'] === "FAIL") {
                shake("#login-password")
            } else {
                setTimeout('reload()', 2000)
            }
        }
    });
}

/**
 * 收藏
 * @param id
 * @param user_id
 */
function like(id, user_id) {
    $.ajax({
        type: "POST",
        url: "favor/save",
        data: {
            roomId: id,
            userId: user_id
        },
        dataType: "json",
        success: function (data) {
            console.log(data);
            layer.msg('收藏成功');
            setTimeout('reload()', 2000)
        }
    });
}


/**
 * 取消收藏
 * @param id
 */
function unLike(id) {
    $.ajax({
        type: "POST",
        url: "favor/delete",
        data: {
            id: id,
        },
        dataType: "json",
        success: function (data) {
            console.log(data);
            layer.msg('取消成功');
            setTimeout('reload()', 2000)
        }
    });
}


/**
 * 取消订单
 * @param id
 */
function cancelOrder(id) {
    $.ajax({
        type: "POST",
        url: "roomOrder/save",
        data: {
            id: id,
            status: '已取消'
        },
        dataType: "json",
        success: function (data) {
            console.log(data);
            layer.msg('取消成功');
            setTimeout('reload()', 2000)
        }
    });
}

/**
 * 删除订单
 * @param id
 */
function deleteOrder(id) {
    $.ajax({
        type: "POST",
        url: "roomOrder/delete",
        data: {
            id: id
        },
        dataType: "json",
        success: function (data) {
            console.log(data);
            layer.msg('删除成功');
            setTimeout('reload()', 2000)
        }
    });
}

/**
 * 保存订单
 */
function saveOrder() {
    let roomId = $.trim($("#roomId").val());
    let month = $.trim($("#guestNo").val());
    let person = $.trim($("#kidsNo").val());

    $.ajax({
        type: "POST",
        url: "roomOrder/saveOrder",
        data: {
            roomId: roomId,
            month: month,
            person: person,
        },
        dataType: "json",
        success: function (data) {
            console.log(data);
            layer.msg('创建成功');
            setTimeout('reload()', 2000)
        }
    });

}

/**
 * 填写评论
 */
function saveComment() {
    let rateCount = $.trim($("#rateCount").val());
    let userId = $.trim($("#sendId").val());
    let roomId = $.trim($("#roomId").val());
    let content = $.trim($("#content").val());

    if (!content) {
        layer.msg('评论内容不能为空！');
        return;
    }

    $.ajax({
        type: "POST",
        url: "comment/save",
        data: {
            userId: userId,
            roomId: roomId,
            rateCount: rateCount,
            content: content
        },
        dataType: "json",
        success: function (data) {
            console.log(data);
            layer.msg('评论成功');
            setTimeout('reload()', 2000)
        }
    });

}

/**
 * 填写反馈
 */
function saveBack() {
    let roomId = $.trim($("#roomId").val());
    let orderId = $.trim($("#orderId").val());
    let sendId = $.trim($("#sendId").val());
    let acceptId = $.trim($("#userId").val());
    let title = $.trim($("#title").val());
    let content = $.trim($("#back_content").val());

    if (!title) {
        layer.msg('反馈标题不能为空！');
        return;
    }

    if (!content) {
        layer.msg('反馈内容不能为空！');
        return;
    }

    $.ajax({
        type: "POST",
        url: "message/save",
        data: {
            roomId: roomId,
            orderId: orderId,
            sendId: sendId,
            acceptId: acceptId,
            title: title,
            content: content,
            status: '未处理'
        },
        dataType: "json",
        success: function (data) {
            console.log(data);
            layer.msg('反馈成功');
            setTimeout('reload()', 2000)
        }
    });

}

/**
 * 支付订单
 * @param id
 */
function pay(id) {
    $.ajax({
        type: "POST",
        url: "roomOrder/pay",
        data: {
            id: id
        },
        dataType: "json",
        success: function (data) {
            console.log(data);
            layer.msg(data['message']);
            setTimeout('reload()', 2000)
        }
    });
}

/**
 * 修改资料
 */
function updateProfile() {
    let name = $.trim($("#name").val());
    let location = $.trim($("#location").val());
    let email = $.trim($("#email").val());
    let phone = $.trim($("#phone").val());
    let idcardNum = $.trim($("#idcardNum").val());
    let about = $.trim($("#about").val());

    if (!name) {
        layer.msg("姓名不能为空");
        return;
    }
    if (!userNameReg(name)) {
        layer.msg("用户名格式错误，支持中文英文");
        return
    }
    if (!email) {
        layer.msg("邮箱不能为空");
        return;
    }
    if (!emailReg(email)) {
        layer.msg("请输入正确的邮箱格式");
        return;
    }
    if (!phone) {
        layer.msg("手机号不能为空");
        return;
    }

    $.ajax({
        type: "POST",
        url: "user/updateProfile",
        data: {
            name: name,
            location: location,
            email: email,
            phone: phone,
            idcardNum: idcardNum,
            about: about
        },
        dataType: "json",
        success: function (data) {
            console.log(data);
            layer.msg(data['message']);
            if (data['code'] === "EXIST_NAME") {
                shake("#name")
            } else if (data['code'] === "EXIST_EMAIL") {
                shake("#email")
            } else if (data['code'] === "EXIST_PHONE") {
                shake("#phone")
            }else {
                setTimeout('reload()', 2000)
            }
        }
    });
}

/**
 * 添加房源
 */
function addRoom() {
    let ownerId = $.trim($("#ownerId").val());
    let title = $.trim($("#title").val());
    let subTitle = $.trim($("#subTitle").val());
    let monthPrice = $.trim($("#monthPrice").val());
    let location = $.trim($("#location").val());
    let info = $.trim($("#info").val());
    let content = $.trim($("#content").val());
    let img = $.trim($("#img").val());
    let images = $.trim($("#images").val());
    let bedroomCount = $.trim($("#bedroomCount").val());
    let parlourCount = $.trim($("#parlourCount").val());
    let restroomCount = $.trim($("#restroomCount").val());
    let bathroomCount = $.trim($("#bathroomCount").val());
    let garage = $.trim($("#garage").val());
    let capacity = $.trim($("#capacity").val());
    let area = $.trim($("#area").val());
    let address = $.trim($("#address").val());
    let type = $.trim($("#type").val());
    let buildYear = $.trim($("#buildYear").val());
    let elevator = $.trim($("#elevator").val());
    let kitchen = $.trim($("#kitchen").val());
    let freeWifi = $.trim($("#freeWifi").val());
    let window = $.trim($("#window").val());
    let metro = $.trim($("#metro").val());
    let rentType = $.trim($("#rentType").val());

    if (!title) {
        layer.msg("标题不能为空");
        return;
    }
    if (!subTitle) {
        layer.msg("副标题不能为空");
        return;
    }
    if (!monthPrice) {
        layer.msg("月租价格不能为空");
        return;
    }
    if (!location) {
        layer.msg("所在地不能为空");
        return;
    }
    if (!img) {
        layer.msg("封面不能为空");
        return;
    }
    if (!images) {
        layer.msg("照片不能为空");
        return;
    }
    if (!bedroomCount) {
        layer.msg("卧室数量不能为空");
        return;
    }
    if (!parlourCount) {
        layer.msg("客厅数量不能为空");
        return;
    }
    if (!restroomCount) {
        layer.msg("卫生间数量不能为空");
        return;
    }
    if (!bathroomCount) {
        layer.msg("浴室数量不能为空");
        return;
    }
    if (!garage) {
        layer.msg("车库情况不能为空");
        return;
    }
    if (!capacity) {
        layer.msg("房屋面积不能为空");
        return;
    }
    if (!area) {
        layer.msg("小区不能为空");
        return;
    }
    if (!address) {
        layer.msg("详细地址不能为空");
        return;
    }
    if (!type) {
        layer.msg("房屋类型不能为空");
        return;
    }
    if (!buildYear) {
        layer.msg("建造年限不能为空");
        return;
    }
    if (!rentType) {
        layer.msg("租赁方式不能为空");
        return;
    }

    $.ajax({
        type: "POST",
        url: "room/save",
        data: {
            ownerId: ownerId,
            title: title,
            subTitle: subTitle,
            monthPrice: monthPrice,
            location: location,
            info: info,
            content: content,
            img: img,
            images: images,
            lookCount: 0
        },
        dataType: "json",
        success: function (data) {
            console.log(data);
            if (data['code'] === "SUCCESS") {
                $.ajax({
                    type: "POST",
                    url: "roomDetail/save",
                    data: {
                        roomId: data['data']['id'],
                        bedroomCount: bedroomCount,
                        parlourCount: parlourCount,
                        restroomCount: restroomCount,
                        bathroomCount: bathroomCount,
                        capacity: capacity,
                        garage: garage,
                        area: area,
                        address: address,
                        type: type,
                        buildYear: buildYear,
                        status: "暂停",
                        elevator: elevator,
                        kitchen: kitchen,
                        freeWifi: freeWifi,
                        window: window,
                        metro: metro,
                        rentType: rentType
                    },
                    dataType: "json",
                    success: function (data) {
                        console.log(data);
                        layer.msg(data['message']);
                        setTimeout('reload()', 2000)
                    }
                });
            } else {
                layer.msg(data['message']);
            }
        }
    });
}

/**
 * 修改房源
 */
function editRoom() {
    let roomId = $.trim($("#roomId").val());
    let detailId = $.trim($("#detailId").val());
    let title = $.trim($("#title").val());
    let subTitle = $.trim($("#subTitle").val());
    let monthPrice = $.trim($("#monthPrice").val());
    let location = $.trim($("#location").val());
    let info = $.trim($("#info").val());
    let content = $.trim($("#content").val());
    let img = $.trim($("#img").val());
    let images = $.trim($("#images").val());
    let bedroomCount = $.trim($("#bedroomCount").val());
    let parlourCount = $.trim($("#parlourCount").val());
    let restroomCount = $.trim($("#restroomCount").val());
    let bathroomCount = $.trim($("#bathroomCount").val());
    let garage = $.trim($("#garage").val());
    let capacity = $.trim($("#capacity").val());
    let area = $.trim($("#area").val());
    let address = $.trim($("#address").val());
    let type = $.trim($("#type").val());
    let buildYear = $.trim($("#buildYear").val());
    let elevator = $.trim($("#elevator").val());
    let kitchen = $.trim($("#kitchen").val());
    let freeWifi = $.trim($("#freeWifi").val());
    let window = $.trim($("#window").val());
    let metro = $.trim($("#metro").val());
    let rentType = $.trim($("#rentType").val());

    if (!title) {
        layer.msg("标题不能为空");
        return;
    }
    if (!subTitle) {
        layer.msg("副标题不能为空");
        return;
    }
    if (!monthPrice) {
        layer.msg("月租价格不能为空");
        return;
    }
    if (!location) {
        layer.msg("所在地不能为空");
        return;
    }
    if (!img) {
        layer.msg("封面不能为空");
        return;
    }
    if (!images) {
        layer.msg("照片不能为空");
        return;
    }
    if (!bedroomCount) {
        layer.msg("卧室数量不能为空");
        return;
    }
    if (!parlourCount) {
        layer.msg("客厅数量不能为空");
        return;
    }
    if (!restroomCount) {
        layer.msg("卫生间数量不能为空");
        return;
    }
    if (!bathroomCount) {
        layer.msg("浴室数量不能为空");
        return;
    }
    if (!garage) {
        layer.msg("车库情况不能为空");
        return;
    }
    if (!capacity) {
        layer.msg("房屋面积不能为空");
        return;
    }
    if (!area) {
        layer.msg("小区不能为空");
        return;
    }
    if (!address) {
        layer.msg("详细地址不能为空");
        return;
    }
    if (!type) {
        layer.msg("房屋类型不能为空");
        return;
    }
    if (!buildYear) {
        layer.msg("建造年限不能为空");
        return;
    }
    if (!rentType) {
        layer.msg("租赁方式不能为空");
        return;
    }

    $.ajax({
        type: "POST",
        url: "room/save",
        data: {
            id: roomId,
            title: title,
            subTitle: subTitle,
            monthPrice: monthPrice,
            location: location,
            info: info,
            content: content,
            img: img,
            images: images,
        },
        dataType: "json",
        success: function (data) {
            console.log(data);
            if (data['code'] === "SUCCESS") {
                $.ajax({
                    type: "POST",
                    url: "roomDetail/save",
                    data: {
                        id: detailId,
                        bedroomCount: bedroomCount,
                        parlourCount: parlourCount,
                        restroomCount: restroomCount,
                        bathroomCount: bathroomCount,
                        capacity: capacity,
                        garage: garage,
                        area: area,
                        address: address,
                        type: type,
                        buildYear: buildYear,
                        elevator: elevator,
                        kitchen: kitchen,
                        freeWifi: freeWifi,
                        window: window,
                        metro: metro,
                        rentType: rentType
                    },
                    dataType: "json",
                    success: function (data) {
                        console.log(data);
                        layer.msg(data['message']);
                        setTimeout('reload()', 2000)
                    }
                });
            } else {
                layer.msg(data['message']);
            }
        }
    });
}


/**
 * 上传封面
 */
function uploadImg() {
    var formdata = new FormData();
    formdata.append("file", $("#img-file").get(0).files[0]);
    $.ajax({
        async: false,
        type: "POST",
        url: "file/upload",
        dataType: "json",
        data: formdata,
        contentType: false,//ajax上传图片需要添加
        processData: false,//ajax上传图片需要添加
        success: function (data) {
            console.log(data);
            layer.msg(data['message']);
            $("#img-preview").attr('src', data['data']);

            $("#img").attr('value', data['data']);
        }
    });
}

/**
 * 上传照片
 */
function uploadImages() {
    let images = $.trim($("#images").val());
    var formdata = new FormData();
    formdata.append("file", $("#images-file").get(0).files[0]);
    $.ajax({
        async: false,
        type: "POST",
        url: "file/upload",
        dataType: "json",
        data: formdata,
        contentType: false,//ajax上传图片需要添加
        processData: false,//ajax上传图片需要添加
        success: function (data) {
            console.log(data);
            layer.msg(data['message']);
            $("#images-preview").append("<div style=\"margin: 5px\" class=\"float-left sides_list_property_thumb\"><img src="+data['data']+" class=\"img-fluid\" alt=\"\"></div>");
            images += data['data'] + ",";
            console.log(images)
            $("#images").attr('value', images);
        }
    });
}

/**
 * 修改密码
 */
function updatePassword() {
    let old = $.trim($("#old").val());
    let password = $.trim($("#password").val());

    if (!old) {
        layer.msg("旧密码不能为空");
        return;
    }
    if (!userPasswordReg(old)) {
        layer.msg("旧密码请输入8-16位字符，支持英文数字下划线");
        return;
    }

    if (!password) {
        layer.msg("新密码不能为空");
        return;
    }
    if (!userPasswordReg(password)) {
        layer.msg("新密码请输入8-16位字符，支持英文数字下划线");
        return;
    }

    $.ajax({
        type: "POST",
        url: "user/changePassword",
        data: {
            old: old,
            password: password,
        },
        dataType: "json",
        success: function (data) {
            console.log(data);
            layer.msg(data['message']);
        }
    });
}

/**
 * 修改房屋状态
 */
function changeRoomStatus(id, status) {
    $.ajax({
        type: "POST",
        url: "roomDetail/save",
        data: {
            id: id,
            status: status,
        },
        dataType: "json",
        success: function (data) {
            console.log(data);
            layer.msg(data['message']);
            setTimeout('reload()', 1000)
        }
    });
}

/**
 * 修改房屋状态
 */
function deleteRoom(id, detailId) {
    $.ajax({
        type: "POST",
        url: "room/delete",
        data: {
            id: id
        },
        dataType: "json",
        success: function (data) {
            $.ajax({
                type: "POST",
                url: "roomDetail/delete",
                data: {
                    id: detailId
                },
                dataType: "json",
                success: function (data) {
                    layer.msg(data['message']);
                    setTimeout('reload()', 1000)
                }
            });
        }
    });
}

/**
 * 删除反馈
 */
function deleteBack(id) {
    $.ajax({
        type: "POST",
        url: "message/delete",
        data: {
            id: id
        },
        dataType: "json",
        success: function (data) {
            layer.msg(data['message']);
            setTimeout('reload()', 1000)
        }
    });
}

function dealBack(id) {
    var remark = prompt("输入处理意见","");
    if (!remark) {
        return
    }
    $.ajax({
        type: "POST",
        url: "message/save",
        data: {
            id: id,
            status: '已接收',
            remark: remark
        },
        dataType: "json",
        success: function (data) {
            layer.msg('处理成功');
            setTimeout('reload()', 1000)
        }
    });
}

/**
 * 抖动函数
 * @param o
 */
function shake(id) {
    let $panel = $(id);
    let box_left = 0;
    $panel.css({'left': box_left, 'position': 'relative', 'color': 'red'});
    for (let i = 0; i < 4; i++) {
        $panel.animate({left: box_left - (20 - 5 * i)}, 30);
        $panel.animate({left: box_left + (20 - 5 * i)}, 30);
    }
}

/**
 * 刷新页面
 */
function reload() {
    window.location.reload();
}

/**
 * 跳转到指定页面
 * @param url
 */
function reloadTo(url) {
    let appCnName = APPLICATION_EN_NAME();
    let href = window.location.href;
    href = href.split("/" + appCnName)[0] + "/" + appCnName + url;
    window.location.href = href;
}


/**
 * 60秒倒计时
 * @param o
 */
function time(o, wait) {
    if (wait === 0) {
        $(o).attr("disabled", false);
        $(o).html("重新发送");
    } else {
        $(o).attr("disabled", true);
        $(o).html(wait + "秒后重新发送");
        wait--;
        setTimeout(function () {
            time(o, wait);
        }, 1000);
    }
}

function sleep(numberMillis) {
    var now = new Date();
    var exitTime = now.getTime() + numberMillis;
    while (true) {
        now = new Date();
        if (now.getTime() > exitTime)
            return;
    }
}

function share() {
    alert("请复制链接后分享：" + window.location.href);
}
