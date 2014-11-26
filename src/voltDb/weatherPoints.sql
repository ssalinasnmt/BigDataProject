
CREATE TABLE weatherpoints (
	maxTemp INTEGER,
	mintemp INTEGER,
	dewPointTemp INTEGER,
	heatIndexTemp INTEGER,
	windChillTemp INTEGER,

	rainAmount INTEGER,
	snowAmount INTEGER,

	probabilityOfPrecipitation INTEGER,

	outlookPercent INTEGER,
	tornadoPercent INTEGER,
	hailPercent INTEGER,
	damagingThunderstormWindPercent INTEGER,
	extremeTornadoesPercent INTEGER,
	extremeHailPercent INTEGER,
	extremeThunderstormWindsPercent INTEGER,
	severeThunderstormPercent INTEGER,
	extremeSevereThunderstormPercent INTEGER,

	sustainedWindSpeed INTEGER,
	cumulative34WindSpeed INTEGER,
	gustWindSpeed INTEGER,

	windDirection INTEGER,

	cloudAmount INTEGER
);
