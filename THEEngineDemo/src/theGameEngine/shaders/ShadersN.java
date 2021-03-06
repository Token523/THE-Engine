package theGameEngine.shaders;

public class ShadersN {

	public static final String lightShader0V = 
			"uniform mat4 u_MVPMatrix;										\n"
			
		+   "attribute vec3 a_position; 									\n"	
		
		+   "void main(){ 													\n"		
		+	"	gl_Position = u_MVPMatrix * vec4(a_position,1.0);			\n"
		+	"}																\n";
	
	public static final String lightShader0F =
			"precision mediump float;																										\n"
			
		+	"void main(){																													\n"
		+	"	vec3 lightColor = vec3(2.0,2.0,2.0) * 0.005;																				\n"
		+	"	gl_FragColor = vec4(lightColor,1.0);																						\n"
		+	"}  																															\n";

	
	public static final String lightShader1V = 
			"uniform mat4 u_MVPMatrix;										\n"
		+	"uniform mat4 u_MVMatrix;										\n"
		+	"uniform mat4 u_MMatrix;										\n"
		+	"uniform mat3 u_NMatrix;										\n"
		+	"uniform vec3 u_lightPos;										\n"
		+	"uniform vec3 u_cameraPos;										\n"
			
		+   "attribute vec3 a_position; 									\n"
		+   "attribute vec3 a_normal;										\n"			
		
		+   "varying vec3 v_worldPos;										\n"
		+   "varying vec3 v_worldNormal;									\n"
		+   "varying vec3 v_surfaceToLight;									\n"
		+   "varying vec3 v_surfaceToCamera;								\n"
		
		+   "void main(){ 													\n"		
		+	"	v_worldPos = vec3(u_MMatrix * vec4(a_position,1.0));		\n"
		+	"	v_worldNormal = normalize(u_NMatrix * a_normal);			\n"
		+	"	v_surfaceToLight = normalize(u_lightPos - v_worldPos);		\n"	
		+	"	v_surfaceToCamera = normalize(u_cameraPos - v_worldPos);	\n"	
		+	"	gl_Position = u_MVPMatrix * vec4(a_position,1.0);			\n"
		+	"}																\n";
	
	public static final String lightShader1F =
			"precision mediump float;																										\n"
			
		+	"uniform vec3 u_lightPos;																										\n"
		
		+	"varying vec3 v_worldPos;																										\n"
		+	"varying vec3 v_worldNormal;																									\n"
		+   "varying vec3 v_surfaceToLight;																									\n"
		+   "varying vec3 v_surfaceToCamera;																								\n"
			
		+	"void main(){																													\n"
		+	"	vec3 lightColor;																											\n"	

		+	"	vec3 ambient = vec3(2.0,2.0,2.0) * 0.005;																					\n"
		+	"	float diffuseCoff = max(0.0,dot(v_worldNormal,v_surfaceToLight));															\n"
		+	"	vec3 diffuse = diffuseCoff * vec3(2.0,2.0,2.0);																				\n"
		+	"	vec3 specular = vec3(0.0,0.0,0.0);																								\n"		
		+	"	if (diffuseCoff > 0.0)																										\n"
		+	"		specular = pow(max(0.0,dot(v_surfaceToCamera, reflect(-v_surfaceToLight,v_worldNormal))), 2.0) * vec3(1.0,1.0,1.0) * vec3(2.0,2.0,2.0);	\n"
		
		+	"	float distanceToLight = length(u_lightPos-v_worldPos);																		\n"			
		+	"	float attenuation = 1.0 / (1.0 + (0.2 * pow(distanceToLight,2.0)));															\n"
		+	"	//float attenuation = 1.0;																									\n"
		
		+	"	lightColor = ambient + attenuation * (diffuse + specular);																	\n"

		+	"	gl_FragColor = vec4(lightColor,1.0);																						\n"
		+	"}  																															\n";
	
}
