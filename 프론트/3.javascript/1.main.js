//이게 뭔데
var canvas = /** @type {HTMLCanvasElement} */ (document.getElementById('canvas'));
var ctx = canvas.getContext('2d');

canvas.width = window.innerWidth - 100;
canvas.height = window.innerHeight - 100;

var dino = {
    x : 20,
    y : 200,
    width : 50,
    height : 50,

    draw(){
        ctx.fillStyle = 'green';
        ctx.fillRect(this.x,this.y,this.width,this.height);
    }
}

/**
 var img1 = new Image();
 img1.src = 'cactus.png';

 **/

class Cactus {
    constructor(){
        this.x = 500;
        this.y = 200; 
        this.width = 50;
        this.height = 50;
    }
    draw(){
        ctx.fillStyle = 'red';
        ctx.fillRect(this.x,this.y,this.width,this.height);
        //ctx.drawImage(img1,this.x,this.y)
    }
}

var timer = 0;
var cactus여러개 = [];
var jumptimer = 0;
var animation;
//프레임마다 실행할 거 
function move(){
    animation = requestAnimationFrame(move);
    timer ++;

    ctx.clearRect(0,0,canvas.width,canvas.height);


    if( timer % 200  === 0){
        var cactus = new Cactus();
        cactus여러개.push(cactus);
    }
    cactus여러개.forEach((a,i,o)=>{
        if( a.x < 0) {
            o.splice(i,1)
        }
        a.x--;

        colision(dino, a);

        a.draw()
    })
    
    if(isjump == true){
        dino.y--;
        jumptimer++;
    }
    if(isjump ==false){
        if (dino.y < 200)
        dino.y++;
    }

    if(jumptimer> 100){
        isjump = false;
        jumptimer = 0;
    }

    dino.draw();
}
move();

//colision check
function colision(dino,cactus){
    var differ_x_axis = cactus.x - (dino.x + dino.width);
    var differ_y_axis = cactus.y - (dino.y + dino.height);
    if(differ_x_axis < 0 && differ_y_axis <0) {
        ctx.clearRect(0,0,canvas.width,canvas.height);
        cancelAnimationFrame(animation);
    }

}


var isjump = false;
document.addEventListener('keydown',function(e){
    if(e.code === 'Space'){
        isjump = true;
    }
})

