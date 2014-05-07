package ist.meic.pa;

import javassist.CannotCompileException;
import javassist.expr.Cast;
import javassist.expr.FieldAccess;

public class TraceExprEditorExtended extends TraceExprEditor {
	public void edit(Cast cast) throws CannotCompileException {
		traceCast(cast);
	}

	public void edit(FieldAccess fieldAccess) throws CannotCompileException {
		traceFieldAccess(fieldAccess);
	}

	private void traceFieldAccess(FieldAccess fieldAccess)
			throws CannotCompileException {
		String template = "";
		if (fieldAccess.isWriter()) {
			template = "{"
					+ "	ist.meic.pa.Trace.addFieldSetElementToHistory($0, \"%s\", \"%s\", %d);"
					+ "	$_ = $proceed($$);"
					+ "}";
		}
		else {
			template = "{"
					+ "	ist.meic.pa.Trace.addFieldGetElementToHistory($0, \"%s\", \"%s\", %d);"
					+ "	$_ = $proceed($$);" + "}";
		}
		
		fieldAccess.replace(String.format(template, fieldAccess.getFieldName(), fieldAccess.getFileName(),
				fieldAccess.getLineNumber()));
	}

	private void traceCast(Cast cast) throws CannotCompileException {
		String template = "{"
				+ "	ist.meic.pa.Trace.addCastElementToHistory($1, $type, \"%s\", %d);"
				+ "	$_ = $proceed($$);" + "}";
		System.err.println("CAST FOUND HERE");
		cast.replace(String.format(template, cast.getFileName(),
				cast.getLineNumber()));
	}

}
