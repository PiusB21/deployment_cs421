import { ref } from 'vue'

class Canvas {
  constructor(canvas, fillColor, strokeColor) {
    this.canvas = canvas
    this.ctx = this.canvas.getContext('2d')
    this.squareSize = 90
    this.resizeCanvas()
    this.drawSquares(fillColor, strokeColor)
  }

  //for when screen is resized(viewport)
  resizeCanvas() {
    const pixelRatio = window.devicePixelRatio || 1

    this.canvas.width = this.canvas.parentElement.offsetWidth * pixelRatio
    //mutliplying like that ensures that the canvas is rendered at a higher resolution
    //Screens with high DPI (like Retina displays) have more physical pixels per CSS pixel
    this.canvas.height = this.canvas.parentElement.offsetHeight * pixelRatio
    this.ctx.scale(pixelRatio, pixelRatio)
  }

  drawSquares(fillColor, strokeColor) {
    const rows = Math.ceil(this.canvas.height / this.squareSize) + 2
    const columns = Math.ceil(this.canvas.width / this.squareSize) + 2

    //clear before drawing
    this.ctx.clearRect(0, 0, this.canvas.width, this.canvas.height)

    for (let x = 0; x < columns; x++) {
      for (let y = 0; y < rows; y++) {
        const isGrey = Math.random() < 0.1
        this.ctx.fillStyle = isGrey ? fillColor : 'transparent'
        this.ctx.fillRect(
          x * this.squareSize,
          y * this.squareSize,
          this.squareSize,
          this.squareSize,
        )
        this.ctx.strokeStyle = strokeColor
        this.ctx.strokeRect(
          x * this.squareSize,
          y * this.squareSize,
          this.squareSize,
          this.squareSize,
        )
      }
    }
  }
}

const canvasArray = ref([])

export const renderCanvas = (canvasElements, fillColor, strokeColor) => {
  canvasElements.forEach((canvas) => {
    let squareCanvas = new Canvas(canvas, fillColor, strokeColor)
    canvasArray.value.push(squareCanvas)
  })

  window.addEventListener('resize', () => {
    canvasArray.value.forEach((canvas) => {
      canvas.resizeCanvas()
      canvas.drawSquares(fillColor, strokeColor)
    })
  })
}
