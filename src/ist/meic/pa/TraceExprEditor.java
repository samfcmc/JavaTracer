package ist.meic.pa;

import javassist.CannotCompileException;
import javassist.NotFoundException;
import javassist.expr.ExprEditor;
import javassist.expr.MethodCall;
import javassist.expr.NewExpr;

public class TraceExprEditor extends ExprEditor {
	public void edit(MethodCall call) throws CannotCompileException {
		traceMethodCall(call);
	}

	public void edit(NewExpr newExpr) throws CannotCompileException {
		traceNewExpr(newExpr);
	}
	
	private void traceMethodCall(MethodCall call) throws CannotCompileException {
		try {
			String template = 
					"{" +
					"	ist.meic.pa.Trace.addUsedAsArgumentElementToHistory($args, \"%s\", \"%s\", %d);" +
					"	$_ = $proceed($$);" +
					"	ist.meic.pa.Trace.addReturnElementToHistory(($w)$_,\"%s\", \"%s\", %d);" +
					"}";
			call.replace(String.format(template,
					call.getMethod().getLongName(), call.getFileName(),
					call.getLineNumber(), call.getMethod().getLongName(),
					call.getFileName(), call.getLineNumber()));
		} catch (NotFoundException e) {
			e.printStackTrace();
		}
	}

	private void traceNewExpr(NewExpr newExpr) throws CannotCompileException {
		try {
			final String template = 
					"{" +
					"	$_ = $proceed($$);" +
					"	ist.meic.pa.Trace.addReturnElementToHistory(($w)$_, \"%s\", \"%s\", %d);" +
					"}";
			newExpr.replace(String.format(template, newExpr
					.getConstructor().getLongName(), newExpr
					.getFileName(), newExpr.getLineNumber()));
		} catch (NotFoundException e) {
			e.printStackTrace();
		}
	}
}
