package com.ipmm

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input
import com.badlogic.gdx.InputProcessor
import com.badlogic.gdx.Screen
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.input.GestureDetector
import com.badlogic.gdx.math.Rectangle
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.math.Vector3

/**
 * Created by Oleg on 17.03.2018.
 */

class MainMenuScreen(internal val game: MainActivity) : Screen, GestureDetector.GestureListener, InputProcessor {
    internal var camera: OrthographicCamera
    internal var texButtonStart: Texture
    internal var texButtonOption: Texture
    internal var texLogo: Texture
    internal var startRect: Rectangle
    internal var optionRect: Rectangle
    internal var logoRect: Rectangle
    internal var width = 720
    internal var height = 1200

    init {

        camera = OrthographicCamera()
        camera.setToOrtho(false, width.toFloat(), height.toFloat())


        texButtonStart = Texture("play-icon.png")
        texButtonOption = Texture("option-icon.png")
        texLogo = Texture("logo.png")
        startRect = Rectangle(36f, 406f, 200f, 200f)
        optionRect = Rectangle(500f, 406f, 200f, 200f)
        logoRect = Rectangle(104f, 500f, 512f, 512f)
    }

    override fun render(delta: Float) {
        Gdx.input.setInputProcessor(this); //нужно для работы кнопок телефона. например, кнопки назад.
        Gdx.input.setCatchBackKey(true);
        Gdx.gl.glClearColor(247f, 247f, 245f, 0f)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)

        camera.update()
        game.batch.projectionMatrix = camera.combined

        game.batch.begin()
        game.font.data.setScale(4f, 4f)
        game.batch.draw(texLogo, logoRect.x, logoRect.y, logoRect.width, logoRect.height)
        game.batch.draw(texButtonStart, startRect.x, startRect.y, startRect.width, startRect.height)
        game.batch.draw(texButtonOption, optionRect.x, optionRect.y, optionRect.width, optionRect.height)
        game.batch.end()

    }

    override fun keyDown(keycode: Int): Boolean {
        if(keycode == Input.Keys.BACK){
            Gdx.app.exit()
        }
        return false;
    }

    override fun touchUp(screenX: Int, screenY: Int, pointer: Int, button: Int): Boolean {
        return false;
    }

    override fun mouseMoved(screenX: Int, screenY: Int): Boolean {
        return false;
    }

    override fun keyTyped(character: Char): Boolean {
        return false;
    }

    override fun scrolled(amount: Int): Boolean {
        return false;
    }

    override fun keyUp(keycode: Int): Boolean {
        return false;
    }

    override fun touchDragged(screenX: Int, screenY: Int, pointer: Int): Boolean {
        return false;
    }

    override fun touchDown(screenX: Int, screenY: Int, pointer: Int, button: Int): Boolean {
        val touchPos = Vector3()
        touchPos.set(Gdx.input.getX(0).toFloat(), Gdx.input.getY(0).toFloat(), 0f)
        camera.unproject(touchPos) //важная функция для того, чтобы подгонять координаты приложения в разных телефонах
        if (startRect.contains(touchPos.x, touchPos.y)) {
            game.screen = GameScreen(game)
            dispose()
        }
        if (optionRect.contains(touchPos.x, touchPos.y)) {
            game.screen = OptionScreen(game)
            dispose()
        }

        return false
    }

    override fun resize(width: Int, height: Int) {}

    override fun show() {
        Gdx.input.inputProcessor = GestureDetector(this)
    }

    override fun hide() {}

    override fun pause() {}

    override fun resume() {}

    override fun dispose() {}

    override fun touchDown(x: Float, y: Float, pointer: Int, button: Int): Boolean {
        return false
    }

    override fun tap(x: Float, y: Float, count: Int, button: Int): Boolean {
        return false
    }

    override fun longPress(x: Float, y: Float): Boolean {
        return false
    }

    override fun fling(velocityX: Float, velocityY: Float, button: Int): Boolean {
        return false
    }

    override fun pan(x: Float, y: Float, deltaX: Float, deltaY: Float): Boolean {
        return false
    }

    override fun panStop(x: Float, y: Float, pointer: Int, button: Int): Boolean {
        return false
    }

    override fun zoom(initialDistance: Float, distance: Float): Boolean {
        return false
    }

    override fun pinch(initialPointer1: Vector2, initialPointer2: Vector2, pointer1: Vector2, pointer2: Vector2): Boolean {
        return false
    }

    override fun pinchStop() {

    }

    companion object {
        var pMines = 30
    }
}