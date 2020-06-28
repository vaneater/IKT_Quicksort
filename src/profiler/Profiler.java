package profiler;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Profiler implements Runnable {
    private Class<? extends ProfiledClass> _clazz;
    private Object[] _arguments;
    private Boolean _succeeded;
    private Boolean _executed;
    private Boolean _running;
    private long _constructorStart;
    private long _constructorFinished;
    private long _executionFinished;
    private long _constructorStartMemory;
    private long _constructorFinishedMemory;
    private List<Long> _measuredMemory;
    private long _executionFinishedMemory;

    public Profiler(Class<? extends ProfiledClass> clazz, Object... arguments) {
        this._clazz = clazz;
        this._arguments = arguments;
        this._running = false;
        this._executed = false;
        this._succeeded = false;
    }

    public void start() {
        this._executed = true;
        this._succeeded = false;
        this._running = false;
        this._constructorStart = System.nanoTime();
        this._measureMemory();
        this._constructorStartMemory = this._measureMemory();
        Constructor constructor = null;

        try {
            if (this._arguments.length == 0) {
                constructor = this._clazz.getConstructor();
            } else {
                Class<?>[] parameterTypes = new Class[this._arguments.length];

                for(int index = 0; index < this._arguments.length; ++index) {
                    parameterTypes[index] = this._arguments[index].getClass();
                }

                constructor = this._clazz.getConstructor(parameterTypes);
            }
        } catch (NoSuchMethodException var19) {
            System.err.println("Failed to load your object with exception:");
            var19.printStackTrace();
            return;
        }

        if (constructor == null) {
            System.err.println("Failed to find a constructor for your object");
        } else {
            try {
                ProfiledClass profiledClass = (ProfiledClass)constructor.newInstance(this._arguments);
                this._constructorFinished = System.nanoTime();
                this._constructorFinishedMemory = this._measureMemory();
                this._running = true;
                Thread thread = new Thread(this);
                thread.start();

                try {
                    profiledClass.run();
                } catch (Exception var13) {
                    var13.printStackTrace();
                } finally {
                    this._running = false;
                }

                thread.join();
                this._executionFinished = System.nanoTime();
                this._executionFinishedMemory = this._measureMemory();
                this._succeeded = true;
                this._running = false;
            } catch (InstantiationException var15) {
                System.err.println("Failed to create an instance of your class with exception:");
                var15.printStackTrace();
            } catch (IllegalAccessException var16) {
                System.err.println("The selected constructor can not be invoked due to access limits:");
                var16.printStackTrace();
            } catch (InvocationTargetException var17) {
                System.err.println("The constructor threw an exception as it was executed:");
                var17.printStackTrace();
            } catch (InterruptedException var18) {
                System.err.println("The monitoring thread was interrupted and failed to join:");
                var18.printStackTrace();
            }

        }
    }

    public void run() {
        this._measuredMemory = new ArrayList();
        this._measuredMemory.add(this._measureMemory());

        while(this._running) {
            this._measuredMemory.add(this._measureMemory());

            try {
                Thread.sleep(50L);
            } catch (InterruptedException var2) {
            }
        }

        this._measuredMemory.add(this._measureMemory());
    }

    public void printResults() {
        if (!this._executed) {
            System.out.println("No results yet - run the start()-method to profile your application.");
        } else if (this._running) {
            System.out.println("Profiler is still running - no results yet.");
        } else if (!this._succeeded) {
            System.out.println("Your application failed to execute - no profiling results are kept.");
        } else {
            System.out.printf("Initial Memory consumed: %d Byte\n", this._constructorStartMemory);
            System.out.println("------------------------------------");
            System.out.printf("Object construction time for %s: %d ms\n", this._clazz.getName(), (this._constructorFinished - this._constructorStart) / 1000000L);
            System.out.printf("Memory consumed: %d Byte\n", Math.max(0L, this._constructorFinishedMemory - this._constructorStartMemory));
            System.out.println("------------------------------------");
            long maxMemory = this._executionFinishedMemory;

            long memory;
            for(Iterator var3 = this._measuredMemory.iterator(); var3.hasNext(); maxMemory = Math.max(maxMemory, memory)) {
                memory = (Long)var3.next();
            }

            System.out.printf("Execution time: %d ms\n", (this._executionFinished - this._constructorFinished) / 1000000L);
            System.out.printf("Execution memory: %d Byte\n", maxMemory - this._constructorStartMemory);
        }
    }

    private long _measureMemory() {
        System.gc();
        Runtime runtime = Runtime.getRuntime();
        return runtime.totalMemory() - runtime.freeMemory();
    }
}