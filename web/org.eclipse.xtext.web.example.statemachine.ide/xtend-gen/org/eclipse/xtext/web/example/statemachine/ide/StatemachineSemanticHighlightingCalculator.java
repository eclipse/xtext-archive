package org.eclipse.xtext.web.example.statemachine.ide;

import java.util.List;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.xtext.ide.editor.syntaxcoloring.DefaultSemanticHighlightingCalculator;
import org.eclipse.xtext.ide.editor.syntaxcoloring.IHighlightedPositionAcceptor;
import org.eclipse.xtext.nodemodel.INode;
import org.eclipse.xtext.nodemodel.util.NodeModelUtils;
import org.eclipse.xtext.web.example.statemachine.statemachine.Command;
import org.eclipse.xtext.web.example.statemachine.statemachine.Event;
import org.eclipse.xtext.web.example.statemachine.statemachine.Signal;
import org.eclipse.xtext.web.example.statemachine.statemachine.StatemachinePackage;
import org.eclipse.xtext.xbase.lib.IterableExtensions;
import org.eclipse.xtext.xbase.lib.Procedures.Procedure1;

@SuppressWarnings("all")
public class StatemachineSemanticHighlightingCalculator extends DefaultSemanticHighlightingCalculator {
  @Override
  protected boolean highlightElement(final EObject it, final IHighlightedPositionAcceptor acceptor) {
    boolean _matched = false;
    if (!_matched) {
      if (it instanceof Signal) {
        _matched=true;
        this.highlightSignal(it, ((Signal)it), StatemachinePackage.Literals.SIGNAL__NAME, acceptor);
      }
    }
    if (!_matched) {
      if (it instanceof Command) {
        _matched=true;
        Signal _signal = ((Command)it).getSignal();
        this.highlightSignal(it, _signal, StatemachinePackage.Literals.COMMAND__SIGNAL, acceptor);
      }
    }
    if (!_matched) {
      if (it instanceof Event) {
        _matched=true;
        Signal _signal = ((Event)it).getSignal();
        this.highlightSignal(it, _signal, StatemachinePackage.Literals.EVENT__SIGNAL, acceptor);
      }
    }
    if (!_matched) {
      return true;
    }
    return false;
  }
  
  protected void highlightSignal(final EObject owner, final Signal signal, final EStructuralFeature feature, final IHighlightedPositionAcceptor acceptor) {
    List<INode> _findNodesForFeature = NodeModelUtils.findNodesForFeature(owner, feature);
    final Procedure1<INode> _function = new Procedure1<INode>() {
      @Override
      public void apply(final INode it) {
        int _offset = it.getOffset();
        int _length = it.getLength();
        EClass _eClass = signal.eClass();
        String _name = _eClass.getName();
        acceptor.addPosition(_offset, _length, _name);
      }
    };
    IterableExtensions.<INode>forEach(_findNodesForFeature, _function);
  }
}