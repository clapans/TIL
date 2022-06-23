let index = {
    init: function(){
        $("#btn-save").on("click", ()=> {
            this.save()
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
            alert("회원가입이 완료됐습니다.")
            location.href = "/blog"
        }).fail(function(err){
            alert(JSON.stringify(err))
        });
    }
}

index.init();