package ist.meic.pa;

import javassist.CannotCompileException;
import javassist.expr.Cast;
import javassist.expr.FieldAccess;

public class TraceExprEditorExtended extends TraceExprEditor {
	@Override
	public void edit(Cast cast) throws CannotCompileException {
		traceCast(cast);
	}

	@Override
	public void edit(FieldAccess fieldAccess) throws CannotCompileException {
		traceFieldAccess(fieldAccess);
	}

	private void traceFieldAccess(FieldAccess fieldAccess)
			throws CannotCompileException {
		String template = "";

		// We will not trace static field accesses (for obvious reasons)
		if (!fieldAccess.isStatic()) {
			if (fieldAccess.isWriter()) {
				template = "{"
						+ "	ist.meic.pa.Tracer.addFieldSetElementToHistory(($w)$0, \"%s\", \"%s\", %d);"
						+ "	$_ = $proceed($$);" + "}";
			} else {
				template = "{"
						+ "	$_ = $proceed($$);"
						+ "	ist.meic.pa.Tracer.addFieldGetElementToHistory(($w)$0, \"%s\", \"%s\", %d);"
						+ "}";
			}
			fieldAccess.replace(String.format(template,
					fieldAccess.getFieldName(), fieldAccess.getFileName(),
					fieldAccess.getLineNumber()));
		}
	}

	private void traceCast(Cast cast) throws CannotCompileException {
		String template = "{"
				+ "	ist.meic.pa.Tracer.addCastElementToHistory(($w)$1, $type, \"%s\", %d);"
				+ "	$_ = $proceed($$);" + "}";
		cast.replace(String.format(template, cast.getFileName(),
				cast.getLineNumber()));
	}

}
