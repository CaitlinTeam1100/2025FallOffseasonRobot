// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;

import edu.wpi.first.apriltag.AprilTagFieldLayout;
import edu.wpi.first.apriltag.AprilTagFields;
import edu.wpi.first.math.Matrix;
import edu.wpi.first.math.VecBuilder;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Rotation3d;
import edu.wpi.first.math.geometry.Transform3d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.geometry.Translation3d;
import edu.wpi.first.math.kinematics.SwerveDriveKinematics;
import edu.wpi.first.math.numbers.N1;
import edu.wpi.first.math.numbers.N3;
import edu.wpi.first.math.util.Units;
import frc.robot.utils.TargetPose;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
  public static class OperatorConstants {
    public static final int kDriverControllerPort = 0;
    public static final String whoLetTheDogsOut = "alan";
  }

  public static class DriveModuleConstants {
  public static final double kWheelDiameterMeters = 0.0762;
  public static final double D_ANGLE_TOLERANCE_DEGREES = 2.5;
  public static final int kDrivingMotorPinionTeeth = 12;
  public static final double kDrivingMotorReduction = (45.0 * 22) / (kDrivingMotorPinionTeeth * 15);

  public static final double kDrivingEncoderPositionFactor = (kWheelDiameterMeters * Math.PI)
        / kDrivingMotorReduction; // meters
    public static final double kDrivingEncoderVelocityFactor = ((kWheelDiameterMeters * Math.PI)
        / kDrivingMotorReduction) / 60.0; // meters per second

    public static final double kTurningEncoderPositionFactor = (2 * Math.PI); // radians
    public static final double kTurningEncoderVelocityFactor = (2 * Math.PI) / 60.0; // radians per second

    public static final double kTurningEncoderPositionPIDMinInput = 0; // radians
    public static final double kTurningEncoderPositionPIDMaxInput = kTurningEncoderPositionFactor; // radians


    public static final double kVortexFreeSpeedRpm = 6784;

     // Invert the turning encoder, since the output shaft rotates in the opposite direction of
    // the steering motor in the MAXSwerve Module.
    public static final boolean kTurningEncoderInverted = true;
    // Calculations required for driving motor conversion factors and feed forward
    public static final double kDrivingMotorFreeSpeedRps = kVortexFreeSpeedRpm / 60;
    public static final double kWheelCircumferenceMeters = kWheelDiameterMeters * Math.PI;
   
    public static final double kDriveWheelFreeSpeedRps = (kDrivingMotorFreeSpeedRps * kWheelCircumferenceMeters)
    / kDrivingMotorReduction; // meters per second


    public static final double kDrivingP = 0.02;
    public static final double kDrivingI = 0;
    public static final double kDrivingD = 0;
    public static final double kDrivingFF = 1 / kDriveWheelFreeSpeedRps;
    public static final double kDrivingMinOutput = -1;
    public static final double kDrivingMaxOutput = 1;

    public static final double kTurningP = 1;
    public static final double kTurningI = 0;
    public static final double kTurningD = 0;
    public static final double kTurningFF = 0;
    public static final double kTurningMinOutput = -1;
    public static final double kTurningMaxOutput = 1;

    public static final IdleMode kDrivingMotorIdleMode = IdleMode.kCoast;
    public static final IdleMode kTurningMotorIdleMode = IdleMode.kBrake;

    public static final int kDrivingMotorCurrentLimit = 80; // amps
    public static final int kTurningMotorCurrentLimit = 20; // amps

    // Chassis angular offsets, in radians, for each module
    public static final double kFrontLeftChassisAngularOffset = -Math.PI / 2;
    public static final double kFrontRightChassisAngularOffset = 0;
    public static final double kBackLeftChassisAngularOffset = Math.PI;
    public static final double kBackRightChassisAngularOffset = Math.PI / 2;
  }

  public static class DriveConstants {
    // Maximum speed of the robot
    public static final double kMaxSpeedMetersPerSecond = 4.92;
    public static final double kMaxAngularSpeed = Math.PI * 2;

    // Chassis configuration
    public static final double kTrackWidth = Units.inchesToMeters(26.5);
    // Distance between centers of right and left wheels on robot
    public static final double kWheelBase = Units.inchesToMeters(26.5);

    public static final Translation2d kFrontLeftLocation = new Translation2d(0.381, 0.381);
    public static final Translation2d kFrontRightLocation = new Translation2d(0.381, -0.381);
    public static final Translation2d kBackLeftLocation = new Translation2d(-0.381, 0.381);
    public static final Translation2d kBackRightLocation = new Translation2d(-0.381, -0.381);
    public static final SwerveDriveKinematics kKinematics = new SwerveDriveKinematics(kFrontLeftLocation,kFrontRightLocation,kBackLeftLocation,kBackRightLocation);
  }

  public static class OIConstants {
    public static final double kDriveDeadband = 0.05;
    public static final boolean kDriveIsFieldRelative = true;
  }

  public static final class FieldLocationConstants {
    public static final double kMidfieldX = 8.75;
    public static final Pose2d kBlueReefCenter = new Pose2d(4.5, 4, Rotation2d.kZero);
    public static final Pose2d kRedReefCenter = new Pose2d(13, 4, Rotation2d.kZero);

    public static final Translation2d kReefLeftScoreTrans = new Translation2d((DriveConstants.kWheelBase/2)+Units.inchesToMeters(5.75), -0.2);
    public static final Translation2d kReefRightScoreTrans = new Translation2d((DriveConstants.kWheelBase/2)+Units.inchesToMeters(5.75), 0.2);//Should be the same but with -y

    public static final TargetPose kRedCoralA1Pose = new TargetPose(new Pose2d(15.878, 0.773, new Rotation2d(Units.degreesToRadians(125))), true);
    public static final TargetPose kRedCoralA2Pose = new TargetPose(new Pose2d(16.858, 1.382, new Rotation2d(Units.degreesToRadians(125))), true);

  }
  
  public static final class VisionConstants {
    public static final AprilTagFieldLayout kTagLayout = AprilTagFieldLayout.loadField(AprilTagFields.k2025Reefscape);
    
    // The standard deviations of our vision estimated poses, which affect correction rate
    // TODO: (Fake values. Experiment and determine estimation noise on an actual robot.)
    public static final Matrix<N3, N1> kSingleTagStdDevs = VecBuilder.fill(4, 4, 8);
    public static final Matrix<N3, N1> kMultiTagStdDevs = VecBuilder.fill(0.5, 0.5, 1);
    
    // Maximum ambiguity accepted as a valid result from the vision systems
    public static final double kMaxValidAmbiguity = 0.2;
    public static final double kMaxZError = 0.75;
    public static final double kMaxRollError = 0.5;
    public static final double kMaxPitchError = 0.5;

    // Camera height from floor in inches
    public static final double kCameraHeight = 9;
    // Camera Width (Y) and Length (x) offsets in inches
    public static final double kCameraWidthOffset = 25.5/2;
    public static final double kCameraLengthOffset = 25.5/2;
    // Camera mount angle in degrees
    public static final double kCameraMountAngleYaw = 45;
    
    
  };
}
