//숙제 데이터
var basket = [
	{"seq":"1", "product":"냉장고", "price":"195000"},
	{"seq":"2", "product":"세탁기", "price":"287000"},
	{"seq":"10", "product":"에어프라이어", "price":"97000"},
];

$(function(){
	$("#btn").click(function(){
		var $data = new Array();
		$data[0] = "20";
		$data[1] = "30";
		$data[2] = "40";
		
		data = JSON.stringify($data);
		
		$.ajax({
			url:"/ajaxok.do",
			type:"GET",
			data: {alldata:data},
			dataType: 'json',
			success:function(response){
				console.log(response);
				console.log(response.message);
				console.log(response.status);
			},
			error:function(error){
				console.log("에러발생");
			}
		});
	});
	
	$("#btn2").click(function(){
		var $data = {
			name : "홍길동",
			id : "hong",
			tel : "01012341234",
			hobby : {
				play : "game",
				study : "java",
			}
		};
		
		$.ajax({
			url: './ajaxok2.do',
			type: 'POST',
			contentType: "application/json",
			//JSON.stringify : 객체를 JSON 문자열로 변환
			data: JSON.stringify($data),
			dataType: 'json',
			success: function(response){
					console.log(response);
					console.log(response.name);
					console.log(response.id);
					console.log(response.tel);
					console.log(response.hobby);
			},
			error: function(error){
				console.log("에러발생");
			}
		})
	})
	
	$("#btn3").click(function(){
		var data = {
			name:"강감찬",
			age:"15"
		}
		$data = JSON.stringify(data);
		
		$.ajax({
			url: './ajaxok3.do',
			type: 'GET',
			contentType: 'application/json',
			data: {data:$data},
			dataType:'json',
			success: function(response){
				console.log(response);
				console.log(response.name);
				console.log(response.age);
			},
			error: function(error){
				console.log(error);
				console.log("에러발생");
			}
		})
	});
	
	$("#btn4").click(function(){
		var arr = [
			["10%","20%","30%"],
			["30","40","50"]
		]
		data = JSON.stringify(arr);
		$.ajax({
			url: './ajaxok4.do',
			type: 'POST',
			contentType: 'application/json', 
			dataType: 'text',
			data: JSON.stringify(arr),
			success: function(response){
				console.log(response);
			},
			error: function(error){
				console.log(error);
				console.log("에러발생");
			}
		})
	})
})