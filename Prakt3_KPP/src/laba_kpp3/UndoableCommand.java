/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package laba_kpp3;

/**
 *
 * @author Student
 */
public interface UndoableCommand extends Command { public void undo();
public void redo();
}

