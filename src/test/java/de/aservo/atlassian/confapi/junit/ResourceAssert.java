package de.aservo.atlassian.confapi.junit;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.HEAD;
import javax.ws.rs.HttpMethod;
import javax.ws.rs.OPTIONS;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import java.lang.reflect.Method;

import static org.junit.Assert.*;

public class ResourceAssert {

    public static void assertResourcePath(
            final Object resource,
            final String path) {

        final Class<?> resourceClass = resource.getClass();
        final Path resourceClassAnnotation = resourceClass.getAnnotation(Path.class);
        assertNotNull(resourceClassAnnotation);
        assertEquals(path, resourceClassAnnotation.value());
    }

    public static void assertResourceMethodGet(
            final Object resource,
            final String subPath,
            final String methodName,
            final Class<?>... parameterTypes) {

        assertResourceMethodInternal(resource, subPath, HttpMethod.GET, methodName, parameterTypes);
    }

    public static void assertResourceMethodGetNoSubPath(
            final Object resource,
            final String methodName,
            final Class<?>... parameterTypes) {

        assertResourceMethodInternal(resource, null, HttpMethod.GET, methodName, parameterTypes);
    }

    public static void assertResourceMethodPost(
            final Object resource,
            final String subPath,
            final String methodName,
            final Class<?>... parameterTypes) {

        assertResourceMethodInternal(resource, subPath, HttpMethod.POST, methodName, parameterTypes);
    }

    public static void assertResourceMethodPostNoSubPath(
            final Object resource,
            final String methodName,
            final Class<?>... parameterTypes) {

        assertResourceMethodInternal(resource, null, HttpMethod.POST, methodName, parameterTypes);
    }

    public static void assertResourceMethodPut(
            final Object resource,
            final String subPath,
            final String methodName,
            final Class<?>... parameterTypes) {

        assertResourceMethodInternal(resource, subPath, HttpMethod.PUT, methodName, parameterTypes);
    }

    public static void assertResourceMethodPutNoSubPath(
            final Object resource,
            final String methodName,
            final Class<?>... parameterTypes) {

        assertResourceMethodInternal(resource, null, HttpMethod.PUT, methodName, parameterTypes);
    }

    public static void assertResourceMethodDelete(
            final Object resource,
            final String subPath,
            final String methodName,
            final Class<?>... parameterTypes) {

        assertResourceMethodInternal(resource, subPath, HttpMethod.DELETE, methodName, parameterTypes);
    }

    public static void assertResourceMethodDeleteNoSubPath(
            final Object resource,
            final String methodName,
            final Class<?>... parameterTypes) {

        assertResourceMethodInternal(resource, null, HttpMethod.DELETE, methodName, parameterTypes);
    }

    private static void assertResourceMethodInternal(
            final Object resource,
            final String subPath,
            final String httpMethod,
            final String methodName,
            final Class<?>... parameterTypes) {

        final Class<?> resourceClass = resource.getClass();
        final Path resourceClassAnnotation = resourceClass.getAnnotation(Path.class);
        assertNotNull(resourceClassAnnotation);

        final Method method;
        try {
            method = resourceClass.getMethod(methodName, parameterTypes);
        } catch (NoSuchMethodException e) {
            fail(String.format("Method %s is not defined", methodName));
            return;
        }

        assertResourceMethodHttpMethodInternal(method, httpMethod);

        final Path methodPath = method.getAnnotation(Path.class);
        if (subPath != null) {
            assertNotNull(methodPath.value());
            assertEquals(subPath, methodPath.value());
        } else {
            assertNull(methodPath);
        }
    }

    private static void assertResourceMethodHttpMethodInternal(
            final Method method,
            final String httpMethod) {

        switch (httpMethod) {
            case HttpMethod.GET: {
                assertNotNull(method.getAnnotation(GET.class));
                break;
            }
            case HttpMethod.POST: {
                assertNotNull(method.getAnnotation(POST.class));
                break;
            }
            case HttpMethod.PUT: {
                assertNotNull(method.getAnnotation(PUT.class));
                break;
            }
            case HttpMethod.DELETE: {
                assertNotNull(method.getAnnotation(DELETE.class));
                break;
            }
            case HttpMethod.HEAD: {
                assertNotNull(method.getAnnotation(HEAD.class));
                break;
            }
            case HttpMethod.OPTIONS: {
                assertNotNull(method.getAnnotation(OPTIONS.class));
                break;
            }
            default: {
                fail(String.format("HTTP method %s not known", httpMethod));
            }
        }
    }

}
