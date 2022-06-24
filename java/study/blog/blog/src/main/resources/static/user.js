let index = {
    init: function(){
        $("#btn-save").on("click", ()=> {
            this.save()
        })
    },
    init_login: function(){
        $("#btn-login").on("click", ()=> {
            this.login()
        })
    },

    save: function(){
        let data = {
            password : $("#pwd").val(),
            email : $("#email").val(),
            username : $("#username").val()
        }

        $.ajax({
            type: "POST",
            url: "/blog/api/member",
            data: JSON.stringify(data),
            contentType: "application/json; charset=utf-8",
            dataType: "json"
        }).done(function(res){
            if (res.status === "OK"){
                alert("회원가입이 완료됐습니다.")
                location.href = "/blog"
            }else{
                alert(res.data)
            }
        }).fail(function(err){
            alert(JSON.stringify(err))
        });
    },

    login: function(){
            let data = {
                password : $("#pwd").val(),
                username : $("#username").val()
            }

            $.ajax({
                type: "POST",
                url: "/blog/api/member/login",
                data: JSON.stringify(data),
                contentType: "application/json; charset=utf-8",
                dataType: "json"
            }).done(function(res){
                if (res.status === "OK"){
                    alert("로그인이 완료됐습니다.")
                    location.href = "/blog"
                }else{
                    alert(res.data)
                }
            }).fail(function(err){
                alert(JSON.stringify(err))
            });
        }
}

index.init()
index.init_login()